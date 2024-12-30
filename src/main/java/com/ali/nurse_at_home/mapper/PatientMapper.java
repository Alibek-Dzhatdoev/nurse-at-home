package com.ali.nurse_at_home.mapper;

import com.ali.nurse_at_home.model.dto.patient.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.patient.PatientFullDto;
import com.ali.nurse_at_home.model.dto.patient.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.model.params.update.PatientUpdateParams;
import org.mapstruct.*;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = AddressMapper.class)
public interface PatientMapper {

    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "userId", source = "userId")
    Patient toPatient(PatientParams params, UUID userId);

    PatientFullDto toFullDto(Patient patient);

    @Mapping(target = "address", source = "addresses", qualifiedByName = "getPrimaryAddress")
    PatientExtendedDto toExtendedDto(Patient patient);

    PatientThinDto toThinDto(Patient patient);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Patient updatePatient(@MappingTarget Patient patient, PatientUpdateParams params);
}
