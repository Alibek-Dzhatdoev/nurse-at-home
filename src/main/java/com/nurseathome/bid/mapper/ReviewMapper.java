package com.nurseathome.bid.mapper;

import com.nurseathome.bid.model.entity.Review;
import com.nurseathome.bid.model.params.ReviewParams;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface ReviewMapper {

    Review toReview(ReviewParams params);
}
