package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.mapper.PatientMapper;
import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.entity.PatientAddress;
import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.model.params.PatientUpdateParams;
import com.ali.nurse_at_home.repository.CityRepository;
import com.ali.nurse_at_home.repository.PatientAddressRepository;
import com.ali.nurse_at_home.repository.PatientRepository;
import com.ali.nurse_at_home.repository.StreetRepository;
import com.ali.nurse_at_home.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static com.ali.nurse_at_home.utils.SecurityContextUtils.getUserIdFromToken;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PatientServiceImpl implements PatientService {

    PatientMapper patientMapper;

    CityRepository cityRepository;
    StreetRepository streetRepository;
    PatientRepository patientRepository;
    PatientAddressRepository patientAddressRepository;

    //TODO не уверен, нужно ли тут возвращать модель пациента (возможно будет дергаться из Oauth)
    @Override
    public PatientFullDto create(PatientParams params) {
        val patient = patientMapper.toPatient(params, getUserIdFromToken());
        val newPatientId = patientRepository.save(patient).getId();
        val address = checkAddressAndReturn(params);
        patientAddressRepository.save(new PatientAddress(patient, address, true));
        return patientRepository.findByIdFetchAddresses(newPatientId)
                .map(patientMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось создать пациента"));
    }

    @Override
    public PatientFullDto getFullById(long id) {
        return patientRepository.findByIdFetchAddresses(id)
                .map(patientMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента"));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientFullDto getFullByToken() {
        return patientRepository.findByUserId(getUserIdFromToken())
                .map(patientMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента"));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientExtendedDto getExtendedById(long id) {
        return patientRepository.findByIdFetchAddresses(id)
                .map(patient -> {
                    val dto = patientMapper.toExtendedDto(patient);
                    dto.setAddress(patientMapper.addressToDto(patient.getAddresses().stream()
                            .filter(PatientAddress::isPrimary).findAny().orElse(null)));
                    return dto;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента"));
    }

    @Override
    public Page<PatientThinDto> getAll(Specification<Patient> patientSpec,
                                       Pageable pageable) {
        return patientRepository.findAll(patientSpec, pageable)
                .map(patientMapper::toThinDto);
    }

    //TODO
    @Override
    @Transactional
    public PatientFullDto patchPatient(long id, PatientUpdateParams params) {
        //обновить инфу о пациенте
        // и если новый адрес указан, то старый становится не основным а этот основным
        return patientRepository.findById(id)
                .map(patient -> {
                    val updated = patientMapper.updatePatient(patient, params);
                    if(!isNull(params.getAddress())) {
                        updated.getAddresses().stream()
                                .filter(PatientAddress::isPrimary)
                                .findFirst()
                                .ifPresent(add -> add.setPrimary(false));
                        updated.getAddresses().add(new PatientAddress(patient, checkAddressAndReturn(params), true));
                    }
                    return patientMapper.toFullDto(patient);
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
    }

    @Override
    public void deleteById(long id) {
        patientRepository.deleteById(id);
    }

    private Address checkAddressAndReturn(PatientUpdateParams params) {
        val city = cityRepository.findById(params.getAddress().getCityId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Неверный ID города"));

//        val street = streetRepository.findById(params.getAddress().getStreetId())
        val street = streetRepository.findById(1L)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Неверный ID улицы"));

        street.setCityId(city.getId()); //TODO удалить как только БД с улицами будет

        if (!street.getCityId().equals(city.getId())) {
            throw new ResponseStatusException(BAD_REQUEST, "Улица с этим ID не находится в указанном городе");
        }

        return patientMapper.paramstoAddress(params.getAddress(), city, street);
    }
}
