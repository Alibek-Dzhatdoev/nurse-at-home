package com.nurseathome.bid.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
public class AddressDto {

    String city;

    String street;

    String building;

    int entrance;

    int apartment;

    Boolean isPrimary;
}
