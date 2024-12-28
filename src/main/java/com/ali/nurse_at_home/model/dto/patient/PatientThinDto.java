package com.ali.nurse_at_home.model.dto.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Сокращенная модель данных пациента (для медсестры)")
public class PatientThinDto {

    @Schema(description = "ID пациента")
    Long id;

    @Schema(description = "Имя пациента")
    String firstname;

    @Schema(description = "Фамилия пациента")
    String lastname;

    @Schema(description = "Номер телефона пациента")
    String mobilePhone;

    @Schema(description = "Активна ли учетная запись пациента")
    Boolean isActive;
}
