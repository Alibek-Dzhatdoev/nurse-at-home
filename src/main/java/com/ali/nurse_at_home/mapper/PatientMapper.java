package com.ali.nurse_at_home.mapper;

import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.params.PatientParams;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE, uses = AddressMapper.class)
public interface PatientMapper {

    Patient toPatient(PatientParams params);

    PatientFullDto toFullDto(Patient patient);

    PatientExtendedDto toExtendedDto(Patient patient);

    PatientThinDto toThinDto(Patient patient);
}
