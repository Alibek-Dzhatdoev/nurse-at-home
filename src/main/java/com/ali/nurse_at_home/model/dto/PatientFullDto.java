package com.ali.nurse_at_home.model.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class PatientFullDto {

    Long id;

    String email;

    String firstName;

    String lastName;

    String mobilePhone;

    LocalDate dateOfBirth;

    List<AddressDto> addresses;
}
