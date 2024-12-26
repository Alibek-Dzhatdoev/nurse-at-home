package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.mapper.AddressMapper;
import com.ali.nurse_at_home.mapper.PatientMapper;
import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.entity.PatientAddress;
import com.ali.nurse_at_home.model.entity.address.City;
import com.ali.nurse_at_home.model.entity.address.Street;
import com.ali.nurse_at_home.model.params.PatientParams;
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
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PatientServiceImpl implements PatientService {

    PatientMapper patientMapper;
    AddressMapper addressMapper;

    CityRepository cityRepository;
    StreetRepository streetRepository;
    PatientRepository patientRepository;
    PatientAddressRepository patientAddressRepository;

    @Override
    @Transactional
    public PatientFullDto create(PatientParams params) {
        val patient = patientMapper.toPatient(params);
        val newPatientId = patientRepository.save(patient).getId();
        val cityAndStreet = checkAddress(params);
        val address = addressMapper.toAddress(params.getAddress());
        address.setCity(cityAndStreet.getFirst());
        address.setStreet(cityAndStreet.getSecond());
        patientAddressRepository.save(new PatientAddress(patient, address, true));
        return patientRepository.findById(newPatientId)
                .map(patientMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось создать пациента"));
    }

    @Override
    public PatientFullDto getFullById(long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента"));
    }

    @Override
    public PatientExtendedDto getExtendedById(long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toExtendedDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента"));
    }

    @Override
    public Page<PatientThinDto> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(patientMapper::toThinDto);
    }

    //TODO
    @Override
    public PatientFullDto patchPatient(long id, PatientParams params) {
        return null;
    }

    @Override
    public void deleteById(long id) {
        patientRepository.deleteById(id);
    }

    private Pair<City, Street> checkAddress(PatientParams params) {
        val city = cityRepository.findById(params.getAddress().getCityId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Неверный ID города"));
        val street = streetRepository.findById(params.getAddress().getStreetId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Неверный ID улицы"));
        return Pair.of(city, street);
    }
}
