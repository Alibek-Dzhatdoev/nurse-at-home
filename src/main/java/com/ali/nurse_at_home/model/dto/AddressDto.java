package com.ali.nurse_at_home.model.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class AddressDto {

    String city;

    String street;

    String building;

    int entrance;

    int apartment;

    boolean isPrimary;
}
