package com.nurseathome.bid.model.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Builder
@JsonInclude(value = NON_NULL)
@FieldDefaults(level = PRIVATE)
public class ApiError {

    int externalServiceStatus;

    String message;
}