package com.nurseathome.bid.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@JsonInclude(NON_NULL)
@Accessors(chain = true)
@FieldDefaults(level = PRIVATE)
public class AddressDto {

    String country;
    String province;
    String area;
    String locality;
    String street;
    String house;
    Integer entrance;
    Integer apartment;
    Integer floor;
    Integer intercom;
    String geoUrl;
    Boolean isPrimary;
}
