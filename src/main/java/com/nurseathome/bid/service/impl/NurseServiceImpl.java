package com.nurseathome.bid.service.impl;

import com.nurseathome.bid.client.OauthClient;
import com.nurseathome.bid.mapper.NurseMapper;
import com.nurseathome.bid.model.dto.nurse.NurseExtendedDto;
import com.nurseathome.bid.model.dto.nurse.NurseFullDto;
import com.nurseathome.bid.model.dto.nurse.NurseThinDto;
import com.nurseathome.bid.model.entity.Nurse;
import com.nurseathome.bid.model.entity.NursePatientBlacklist;
import com.nurseathome.bid.model.entity.Patient;
import com.nurseathome.bid.model.entity.Procedure;
import com.nurseathome.bid.model.params.NurseParams;
import com.nurseathome.bid.model.params.update.NurseUpdateParams;
import com.nurseathome.bid.repository.BlacklistRepository;
import com.nurseathome.bid.repository.NurseRepository;
import com.nurseathome.bid.repository.PatientRepository;
import com.nurseathome.bid.repository.ProcedureRepository;
import com.nurseathome.bid.service.AddressService;
import com.nurseathome.bid.service.NurseService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import static com.nurseathome.bid.model.enums.Initiator.PATIENT;
import static com.nurseathome.bid.model.enums.Roles.NURSE;
import static com.nurseathome.bid.utils.JwtUtils.getSsoUserIdFromToken;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NurseServiceImpl implements NurseService {

    NurseMapper nurseMapper;

    OauthClient oauthClient;

    AddressService addressService;

    NurseRepository nurseRepository;
    PatientRepository patientRepository;
    ProcedureRepository procedureRepository;
    BlacklistRepository blacklistRepository;

    @Override
    @Transactional
    public NurseFullDto create(NurseParams params) {
        val nurse = nurseMapper.toNurse(params, getSsoUserIdFromToken());
        nurse.setAddress(addressService.checkAddressAndReturn(params.getAddress()));
        nurse.setProcedures(checkProcedures(params.getProcedureIds()));
        val newNurse = nurseRepository.save(nurse);
        oauthClient.endRegistration(NURSE);
        return nurseMapper.toFullDto(newNurse);
    }

    @Override
    @Transactional
    public NurseFullDto updateById(long id, NurseUpdateParams params) {
        return updateNurse(() -> nurseRepository.findById(id), params);
    }

    @Override
    @Transactional
    public NurseFullDto updateByToken(NurseUpdateParams params) {
        return updateNurse(() -> nurseRepository.findBySsoUserId(getSsoUserIdFromToken()), params);
    }

    private NurseFullDto updateNurse(Supplier<Optional<Nurse>> nurseSupplier, NurseUpdateParams params) {
        List<Procedure> procedures = checkProcedures(params.getProcedureIds());
        val address = addressService.checkAddressAndReturn(params.getAddress());
        return nurseSupplier.get()
                .map(nurse -> nurseMapper.update(nurse, params))
                .map(nurse -> {
                    nurse.setProcedures(procedures);
                    nurse.setAddress(address);
                    return nurseMapper.toFullDto(nurseRepository.save(nurse));
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось обновить медсестру"));
    }

    private List<Procedure> checkProcedures(List<Long> procedureIds) {
        List<Procedure> procedures = procedureRepository.findAllById(procedureIds);
        if (procedures.size() < procedureIds.size()) {
            throw new ResponseStatusException(BAD_REQUEST, "Укажите корректные ID процедур");
        }
        return procedures;
    }

    @Override
    public NurseFullDto getByToken() {
        return nurseRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(nurseMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти медсестру"));
    }

    @Override
    public NurseFullDto getFullById(long id) {
        return nurseRepository.findById(id)
                .map(nurseMapper::toFullDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти медсестру"));
    }

    @Override
    public NurseExtendedDto getExtendedById(long id) {
        return nurseRepository.findById(id)
                .map(nurseMapper::toExtendedDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Не удалось найти медсестру"));
    }

    @Override
    public void setIsAvailable(boolean isAvailable) {
        nurseRepository.setIsAvailable(isAvailable, getSsoUserIdFromToken());
    }

    @Override
    public void setIsActive(UUID ssoUserId, boolean isActive) {
        nurseRepository.setIsActive(getSsoUserIdFromToken(), isActive);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NurseThinDto> getFromDoneBids(Pageable pageable) {
        return patientRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(patient -> nurseRepository.findNursesByPatientIdAndBidIsDone(patient.getId(), pageable))
                .map(page -> page.map(nurseMapper::toThinDto))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NurseThinDto> getBlacklist(Pageable pageable) {
        val nurseIds = patientRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(patient -> patient.getBlackList().stream()
                        .filter(black -> black.getInitiator() == PATIENT)
                        .map(NursePatientBlacklist::getNurseId)
                        .toList())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
        return nurseRepository.findAllByIdIn(nurseIds, pageable).map(nurseMapper::toThinDto);
    }

    @Override
    @Transactional
    public void addNurseToBlacklist(long nurseId) {
        val patientId = patientRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(Patient::getId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
        blacklistRepository.findBlackListNurse(patientId, nurseId)
                .orElseGet(() -> blacklistRepository.save(
                        new NursePatientBlacklist(patientId, nurseId, PATIENT)));
    }

    @Override
    public Page<NurseThinDto> removeNurseFromBlacklist(long id, Pageable pageable) {
        return patientRepository.findBySsoUserId(getSsoUserIdFromToken())
                .map(patient -> {
                    patient.setBlackList(patient.getBlackList().stream()
                            .filter(black -> black.getInitiator() == PATIENT)
                            .filter(black -> black.getNurseId() != id)
                            .toList());
                    val nurseIds = patientRepository.save(patient)
                            .getBlackList().stream()
                            .map(NursePatientBlacklist::getNurseId)
                            .toList();
                    return nurseRepository.findAllByIdIn(nurseIds, pageable)
                            .map(nurseMapper::toThinDto);
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
    }
}
