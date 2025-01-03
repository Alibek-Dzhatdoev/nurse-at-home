package com.ali.nurse_at_home.mapper;

import com.ali.nurse_at_home.model.dto.nurse.NurseExtendedDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseFullDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseThinDto;
import com.ali.nurse_at_home.model.entity.Nurse;
import com.ali.nurse_at_home.model.params.NurseParams;
import com.ali.nurse_at_home.model.params.update.NurseUpdateParams;
import org.mapstruct.*;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = AddressMapper.class)
public interface NurseMapper {

    @Mapping(target = "userId", source = "userId")
    Nurse toNurse(NurseParams params, UUID userId);

    NurseFullDto toFullDto(Nurse nurse);

    NurseExtendedDto toExtendedDto(Nurse nurse);

    NurseThinDto toThinDto(Nurse nurse);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Nurse update(@MappingTarget Nurse nurse, NurseUpdateParams params);
}
