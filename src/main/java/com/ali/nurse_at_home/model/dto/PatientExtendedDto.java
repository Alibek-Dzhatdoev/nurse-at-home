package com.ali.nurse_at_home.model.dto;

import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class PatientExtendedDto {

    Long id;

    String firstName;  // Имя пациента

    String lastName;   // Фамилия пациента

    String mobilePhone;  // Номер телефона пациента

    LocalDate dateOfBirth;  // Дата рождения пациента (по желанию)

    AddressDto address;  // Адрес пациента (может быть несколько, но обычно один основной)

    Boolean isActive;
}
