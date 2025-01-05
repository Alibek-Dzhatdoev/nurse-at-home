package com.nurseathome.bid.service;

import com.nurseathome.bid.model.dto.patient.PatientExtendedDto;
import com.nurseathome.bid.model.dto.patient.PatientFullDto;
import com.nurseathome.bid.model.dto.patient.PatientThinDto;
import com.nurseathome.bid.model.entity.Patient;
import com.nurseathome.bid.model.params.PatientParams;
import com.nurseathome.bid.model.params.update.PatientUpdateParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PatientService {

    PatientFullDto create(PatientParams params);

    PatientFullDto getFullById(long id);

    PatientFullDto getFullByToken();

    PatientExtendedDto getExtendedById(long id);

    Page<PatientThinDto> getAll(Specification<Patient> patientSpec, Pageable pageable);

    PatientFullDto updateById(long id, PatientUpdateParams params);

    PatientFullDto updateByToken(PatientUpdateParams params);

    void deleteById(long id);

    Page<PatientThinDto> getBlackList(Pageable pageable);

    void addToBlacklist(long id);

    Page<PatientThinDto> removeFromBlacklist(long id, Pageable pageable);

}
