package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.mapper.NurseMapper;
import com.ali.nurse_at_home.model.dto.nurse.NurseExtendedDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseFullDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseThinDto;
import com.ali.nurse_at_home.model.entity.Nurse;
import com.ali.nurse_at_home.model.entity.NursePatientBlacklist;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.entity.Procedure;
import com.ali.nurse_at_home.model.params.NurseParams;
import com.ali.nurse_at_home.model.params.update.NurseUpdateParams;
import com.ali.nurse_at_home.repository.BlacklistRepository;
import com.ali.nurse_at_home.repository.NurseRepository;
import com.ali.nurse_at_home.repository.PatientRepository;
import com.ali.nurse_at_home.repository.ProcedureRepository;
import com.ali.nurse_at_home.service.AddressService;
import com.ali.nurse_at_home.service.NurseService;
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
import java.util.function.Supplier;

import static com.ali.nurse_at_home.model.enums.Initiator.PATIENT;
import static com.ali.nurse_at_home.utils.SecurityContextUtils.getUserIdFromToken;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NurseServiceImpl implements NurseService {

    NurseMapper nurseMapper;

    AddressService addressService;

    NurseRepository nurseRepository;
    PatientRepository patientRepository;
    ProcedureRepository procedureRepository;
    BlacklistRepository blacklistRepository;

    @Override
    @Transactional
    public NurseFullDto create(NurseParams params) {
        val nurse = nurseMapper.toNurse(params, getUserIdFromToken());
        val address = addressService.checkAddressAndReturn(params.getAddress());
        nurse.setAddress(address);
        nurse.setProcedures(checkProcedures(params.getProcedureIds()));
        return nurseMapper.toFullDto(nurseRepository.save(nurse));
    }

    @Override
    @Transactional
    public NurseFullDto updateById(long id, NurseUpdateParams params) {
        return updateNurse(() -> nurseRepository.findById(id), params);
    }

    @Override
    @Transactional
    public NurseFullDto updateByToken(NurseUpdateParams params) {
        return updateNurse(() -> nurseRepository.findByUserId(getUserIdFromToken()), params);
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
        if (procedures.size() < procedureIds.size())
            throw new ResponseStatusException(BAD_REQUEST, "Укажите корректные ID процедур");
        return procedures;
    }

    @Override
    public NurseFullDto getByToken() {
        return nurseRepository.findByUserId(getUserIdFromToken())
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

    //TODO
    @Override
    public void deleteById(long id) {
        throw new ResponseStatusException(NOT_IMPLEMENTED);
//        nurseRepository.findById(id).ifPresent(nurse -> {
//            nurse.setIsActive(false);
//            nurseRepository.save(nurse);
//        });
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NurseThinDto> getFromDoneBids(Pageable pageable) {
        return patientRepository.findByUserId(getUserIdFromToken())
                .map(patient -> nurseRepository.findNursesByPatientIdAndBidIsDone(patient.getId(), pageable))
                .map(page -> page.map(nurseMapper::toThinDto))
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<NurseThinDto> getBlacklist(Pageable pageable) {
        val nurseIds = patientRepository.findByUserId(getUserIdFromToken())
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
        val patientId = patientRepository.findByUserId(getUserIdFromToken())
                .map(Patient::getId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Пациент не найден"));
        blacklistRepository.findBlackListNurse(patientId, nurseId)
                .orElseGet(() -> blacklistRepository.save(new NursePatientBlacklist(patientId, nurseId, PATIENT)));
    }

    @Override
    public Page<NurseThinDto> removeNurseFromBlacklist(long id, Pageable pageable) {
        return patientRepository.findByUserId(getUserIdFromToken())
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
