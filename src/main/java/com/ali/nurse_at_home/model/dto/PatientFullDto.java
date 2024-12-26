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

    String email;  // Электронная почта

    String firstName;  // Имя пациента

    String lastName;   // Фамилия пациента

    String phoneNumber;  // Номер телефона пациента

    LocalDate dateOfBirth;  // Дата рождения пациента (по желанию)

    List<AddressDto> addresses;  // Адрес пациента (может быть несколько, но обычно один основной)
}
