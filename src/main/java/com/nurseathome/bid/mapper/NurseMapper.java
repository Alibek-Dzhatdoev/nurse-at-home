package com.nurseathome.bid.mapper;

import com.nurseathome.bid.model.dto.nurse.NurseExtendedDto;
import com.nurseathome.bid.model.dto.nurse.NurseFullDto;
import com.nurseathome.bid.model.dto.nurse.NurseThinDto;
import com.nurseathome.bid.model.entity.Nurse;
import com.nurseathome.bid.model.params.NurseParams;
import com.nurseathome.bid.model.params.update.NurseUpdateParams;
import org.mapstruct.*;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = AddressMapper.class)
public interface NurseMapper {

    @Mapping(target = "ssoUserId", source = "userId")
    @Mapping(target = "address", ignore = true)
    Nurse toNurse(NurseParams params, UUID userId);

    NurseFullDto toFullDto(Nurse nurse);

    NurseExtendedDto toExtendedDto(Nurse nurse);

    NurseThinDto toThinDto(Nurse nurse);

    @Mapping(target = "address", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Nurse update(@MappingTarget Nurse nurse, NurseUpdateParams params);
}
