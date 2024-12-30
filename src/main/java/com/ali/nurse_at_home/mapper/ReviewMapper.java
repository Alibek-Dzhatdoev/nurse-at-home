package com.ali.nurse_at_home.mapper;

import com.ali.nurse_at_home.model.dto.ReviewDto;
import com.ali.nurse_at_home.model.entity.Review;
import com.ali.nurse_at_home.model.params.ReviewParams;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface ReviewMapper {

    Review toReview(ReviewParams params);

    ReviewDto toDto(Review review);
}
