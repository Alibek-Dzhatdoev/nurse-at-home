package com.ali.nurse_at_home.model.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class PatientThinDto {

    Long id;

    String firstName;  // Имя пациента

    String lastName;   // Фамилия пациента

    String mobilePhone;  // Номер телефона пациента
}
