package com.ali.nurse_at_home.model.dto.patient;

import com.ali.nurse_at_home.model.dto.AddressDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
public class PatientFullDto {

    @Schema(description = "ID пациента")
    Long id;

    @Schema(description = "Email пациента")
    String email;

    @Schema(description = "Имя пациента")
    String firstname;

    @Schema(description = "Фамилия пациента")
    String lastname;

    @Schema(description = "Номер телефона пациента")
    String mobilePhone;

    @Schema(description = "Дата рождения пациента")
    LocalDate dateOfBirth;

    @Schema(description = "Активна ли учетная запись пациента")
    Boolean isActive;

    @Schema(description = "Адрес пациента (может быть несколько, но один основной")
    List<AddressDto> addresses;
}
