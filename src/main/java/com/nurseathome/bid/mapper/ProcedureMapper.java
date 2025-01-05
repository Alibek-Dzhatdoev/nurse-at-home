package com.nurseathome.bid.mapper;

import com.nurseathome.bid.model.dto.ProcedureDto;
import com.nurseathome.bid.model.dto.ProcedureThinDto;
import com.nurseathome.bid.model.entity.Procedure;
import com.nurseathome.bid.model.params.ProcedureParams;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProcedureMapper {

    Procedure toProcedure(ProcedureParams params);

    ProcedureThinDto toThinDto(Procedure procedure);

    ProcedureDto toDto(Procedure procedure);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Procedure update(@MappingTarget Procedure procedure, ProcedureParams params);
}
