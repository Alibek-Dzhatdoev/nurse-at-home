package com.nurseathome.bid.mapper;

import com.nurseathome.bid.model.dto.patient.PatientExtendedDto;
import com.nurseathome.bid.model.dto.patient.PatientFullDto;
import com.nurseathome.bid.model.dto.patient.PatientThinDto;
import com.nurseathome.bid.model.entity.Patient;
import com.nurseathome.bid.model.params.PatientParams;
import com.nurseathome.bid.model.params.update.PatientUpdateParams;
import org.mapstruct.*;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = AddressMapper.class)
public interface PatientMapper {

    @Mapping(target = "ssoUserId", source = "userId")
    Patient toPatient(PatientParams params, UUID userId);

    PatientFullDto toFullDto(Patient patient);

    PatientExtendedDto toExtendedDto(Patient patient);

    PatientThinDto toThinDto(Patient patient);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Patient updatePatient(@MappingTarget Patient patient, PatientUpdateParams params);
}