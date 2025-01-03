package com.ali.nurse_at_home.service;

import com.ali.nurse_at_home.model.dto.patient.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.patient.PatientFullDto;
import com.ali.nurse_at_home.model.dto.patient.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.model.params.update.PatientUpdateParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PatientService {

    PatientFullDto create(PatientParams params);

    PatientFullDto getFullById(long id);

    PatientFullDto getFullByToken();

    PatientExtendedDto getExtendedById(long id);

    Page<PatientThinDto> getAll(Specification<Patient> patientSpec, Pageable pageable);

    PatientFullDto patchPatient(long id, PatientUpdateParams params);

    void deleteById(long id);

    Page<PatientThinDto> getBlackList(Pageable pageable);

    void addToBlacklist(long id);

    Page<PatientThinDto> removeFromBlacklist(long id, Pageable pageable);

}
