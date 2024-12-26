package com.ali.nurse_at_home.model.params;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class PatientParams {

    @NotBlank
    String firstName;  // Имя пациента

    @NotBlank
    String lastName;   // Фамилия пациента

    @NotBlank
    String phoneNumber;  // Номер телефона пациента

    @NotBlank
    String email;  // Электронная почта

    LocalDate dateOfBirth;  // Дата рождения пациента (по желанию)

    AddressParams address;
}
