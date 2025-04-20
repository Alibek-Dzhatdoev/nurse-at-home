package com.nurseathome.bid.model.dto.patient;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Расширенная модель данных пациента (для медсестры)")
public class PatientExtendedDto {

    @Schema(description = "ID пациента")
    Long id;

    @Schema(description = "Имя пациента")
    String firstname;

    @Schema(description = "Фамилия пациента")
    String lastname;

    @Schema(description = "Дата рождения пациента")
    LocalDate dateOfBirth;

    @Schema(description = "Активна ли учетная запись пациента")
    Boolean isActive;
}
