package com.ali.nurse_at_home.service;

import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.params.PatientParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

    PatientFullDto create(PatientParams params);

    PatientFullDto getFullById(long id);

    PatientExtendedDto getExtendedById(long id);

    Page<PatientThinDto> getAll(Pageable pageable);

    PatientFullDto patchPatient(long id, PatientParams params);

    void deleteById(long id);
}
