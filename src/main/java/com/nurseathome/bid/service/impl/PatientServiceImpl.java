package com.nurseathome.bid.service.impl;

import com.nurseathome.bid.client.OauthClient;
import com.nurseathome.bid.mapper.PatientMapper;
import com.nurseathome.bid.model.dto.patient.PatientExtendedDto;
import com.nurseathome.bid.model.dto.patient.PatientFullDto;
import com.nurseathome.bid.model.dto.patient.PatientThinDto;
import com.nurseathome.bid.model.entity.Nurse;
import com.nurseathome.bid.model.entity.NursePatientBlacklist;
import com.nurseathome.bid.model.entity.Patient;
import com.nurseathome.bid.model.entity.PatientAddress;
import com.nurseathome.bid.model.params.PatientParams;
import com.nurseathome.bid.model.params.update.PatientUpdateParams;
import com.nurseathome.bid.repository.BlacklistRepository;
import com.nurseathome.bid.repository.NurseRepository;
import com.nurseathome.bid.repository.PatientRepository;
import com.nurseathome.bid.repository.address.PatientAddressRepository;
import com.nurseathome.bid.service.AddressService;
import com.nurseathome.bid.service.PatientService;
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
import java.util.UUID;

import static com.nurseathome.bid.model.enums.Initiator.NURSE;
import static com.nurseathome.bid.model.enums.Roles.PATIENT;
import static com.nurseathome.bid.utils.JwtUtils.getCurrentUserEmail;
import static com.nurseathome.bid.utils.JwtUtils.getSsoUserIdFromToken;
import static java.util.List.of;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PatientServiceImpl implements PatientService {

    PatientMapper patientMapper;

    OauthClient oauthClient;

    AddressService addressService;
    NurseRepository nurseRepository;
    PatientRepository patientRepository;
    BlacklistRepository blacklistRepository;
    PatientAddressRepository patientAddressRepository;

    //TODO не уверен, нужно ли тут возвращать модель пациента (возможно будет дергаться из Oauth)
    @Override
    @Transactional
    public PatientFullDto create(PatientParams params) {
        val address = addressService.checkAddressAndReturn(params.getAddress());
        val patient = patientMapper.toPatient(params, getSsoUserIdFromToken());
        patient.setEmail(getCurrentUserEmail())
                .setAddresses(of(new PatientAddress(patient, address, true)))
                .setIsActive(true);
        val newPatient = patientRepository.save(patient);
        oauthClient.endRegistration(PATIENT);
        return patientMapper.toFullDto(newPatient);
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
        return patientRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(patientMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти пациента"));
    }

    @Override
    @Transactional(readOnly = true)
    public PatientExtendedDto getExtendedById(long id) {
        return patientRepository.findByIdFetchAddresses(id)
                .map(patientMapper::toExtendedDto)
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
    public PatientFullDto updateById(long id, PatientUpdateParams params) {
        return patientRepository.findById(id)
                .map(patient -> patientMapper.updatePatient(patient, params))
                .map(patient -> patientMapper.toFullDto(updateAddressIfNeed(params, patient)))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
    }

    @Override
    @Transactional
    public PatientFullDto updateByToken(PatientUpdateParams params) {
        return patientRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(patient -> patientMapper.updatePatient(patient, params))
                .map(patient -> patientMapper.toFullDto(updateAddressIfNeed(params, patient)))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                        "Не удалось найти пациента и обновить данные"));
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
                    .map(addr -> addr.setIsPrimary(true))
                    .orElse(patientAddressRepository.save(new PatientAddress(patient, address, true)));
            patient.getAddresses().add(patientAddress);
            patientRepository.save(patient);
        }
        return patient;
    }

    @Override
    public void setIsActive(UUID ssoUserId, boolean isActive) {
        patientRepository.setIsActive(getSsoUserIdFromToken(), isActive);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PatientThinDto> getBlackList(Pageable pageable) {
        List<Long> patientIds = nurseRepository.findBySsoUserId(getSsoUserIdFromToken())
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
        val nurseId = nurseRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(Nurse::getId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Медсестра не найдена"));
        blacklistRepository.findBlackListNurse(nurseId, patientId)
                .orElseGet(
                        () -> blacklistRepository.save(new NursePatientBlacklist(nurseId, patientId, NURSE)));
    }

    //TODO
    @Override
    public Page<PatientThinDto> removeFromBlacklist(long id, Pageable pageable) {
        return nurseRepository.findBySsoUserId(getSsoUserIdFromToken())
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
