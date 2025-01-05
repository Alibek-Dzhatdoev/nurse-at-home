package com.nurseathome.bid.mapper;

import com.nurseathome.bid.model.entity.Bid;
import com.nurseathome.bid.model.params.BidParams;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface BidMapper {

    @Mapping(target = "status", constant = "SEARCHING")
    Bid toBid(BidParams params);
}
