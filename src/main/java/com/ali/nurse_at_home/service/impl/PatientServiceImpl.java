package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.mapper.AddressMapper;
import com.ali.nurse_at_home.mapper.PatientMapper;
import com.ali.nurse_at_home.model.dto.patient.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.patient.PatientFullDto;
import com.ali.nurse_at_home.model.dto.patient.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Nurse;
import com.ali.nurse_at_home.model.entity.NursePatientBlacklist;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.entity.PatientAddress;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.model.params.update.PatientUpdateParams;
import com.ali.nurse_at_home.repository.BlacklistRepository;
import com.ali.nurse_at_home.repository.NurseRepository;
import com.ali.nurse_at_home.repository.PatientAddressRepository;
import com.ali.nurse_at_home.repository.PatientRepository;
import com.ali.nurse_at_home.service.AddressService;
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

import java.util.List;

import static com.ali.nurse_at_home.model.enums.Initiator.NURSE;
import static com.ali.nurse_at_home.utils.SecurityContextUtils.getUserIdFromToken;
import static java.util.List.of;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PatientServiceImpl implements PatientService {

    PatientMapper patientMapper;
    AddressMapper addressMapper;

    AddressService addressService;
    NurseRepository nurseRepository;
    PatientRepository patientRepository;
    BlacklistRepository blacklistRepository;
    PatientAddressRepository patientAddressRepository;

    //TODO не уверен, нужно ли тут возвращать модель пациента (возможно будет дергаться из Oauth)
    @Override
    @Transactional
    public PatientFullDto create(PatientParams params) {
        val patient = patientMapper.toPatient(params, getUserIdFromToken());
        val address = addressService.checkAddressAndReturn(params.getAddress());
        patient.setAddresses(of(new PatientAddress(patient, address, true)));
        return patientMapper.toFullDto(patientRepository.save(patient));
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
                    dto.setAddress(addressMapper.toDto(patient.getAddresses().stream()
                            .filter(PatientAddress::getIsPrimary).findAny().orElse(null)));
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

    @Override
    @Transactional
    public PatientFullDto patchPatient(long id, PatientUpdateParams params) {
        return patientRepository.findById(id)
                .map(patient -> patientMapper.updatePatient(patient, params))
                .map(patient -> patientMapper.toFullDto(updateAddressIfNeed(params, patient)))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
    }

    private Patient updateAddressIfNeed(PatientUpdateParams params, Patient patient) {
        if (!isNull(params.getAddress())) {
            patient.getAddresses().stream()
                    .filter(PatientAddress::getIsPrimary)
                    .findFirst()
                    .ifPresent(address -> address.setIsPrimary(false));
            val address = addressService.checkAddressAndReturn(params.getAddress());
            val patientAddress = patientAddressRepository
                    .findByPatientIdAndAddressId(patient.getId(), address.getId())
                    .map(addr -> {
                        addr.setIsPrimary(true);
                        return addr;
                    })
                    .orElse(patientAddressRepository.save(new PatientAddress(patient, address, true)));
            patient.getAddresses().add(patientAddress);
            patientRepository.save(patient);
        }
        return patient;
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        patientRepository.findById(id).ifPresent(patient -> {
            patient.setIsActive(false);
            patientRepository.save(patient);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientThinDto> getBlackList(Pageable pageable) {
        List<Long> patientIds = nurseRepository.findByUserId(getUserIdFromToken())
                .map(nurse -> nurse.getBlackList().stream()
                        .filter(b -> b.getInitiator() == NURSE)
                        .map(NursePatientBlacklist::getPatientId)
                        .toList())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти медсестру"));
        return patientRepository.findAllByIdIn(patientIds, pageable).map(patientMapper::toThinDto);
    }

    @Override
    @Transactional
    public void addToBlacklist(long patientId) {
        val nurseId = nurseRepository.findByUserId(getUserIdFromToken())
                .map(Nurse::getId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Медсестра не найдена"));
        blacklistRepository.findBlackListNurse(nurseId, patientId)
                .orElseGet(() -> blacklistRepository.save(new NursePatientBlacklist(nurseId, patientId, NURSE)));
    }

    //TODO
    @Override
    public Page<PatientThinDto> removeFromBlacklist(long id, Pageable pageable) {
        return nurseRepository.findByUserId(getUserIdFromToken())
                .map(nurse -> {
                    nurse.setBlackList(nurse.getBlackList().stream()
                            .filter(black -> black.getInitiator() == NURSE)
                            .filter(black -> black.getPatientId() != id)
                            .toList());
                    val patientIds = nurseRepository.save(nurse)
                            .getBlackList().stream()
                            .map(NursePatientBlacklist::getPatientId)
                            .toList();
                    return patientRepository.findAllByIdIn(patientIds, pageable)
                            .map(patientMapper::toThinDto);
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Медсестра не найдена"));
    }
}
