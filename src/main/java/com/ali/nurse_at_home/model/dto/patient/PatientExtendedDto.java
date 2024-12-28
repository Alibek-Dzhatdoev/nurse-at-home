package com.ali.nurse_at_home.model.dto.patient;

import com.ali.nurse_at_home.model.dto.AddressDto;
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

    @Schema(description = "Номер телефона пациента")
    String mobilePhone;

    @Schema(description = "Дата рождения пациента")
    LocalDate dateOfBirth;

    @Schema(description = "Адрес пациента (может быть несколько, но один основной")
    AddressDto address;

    @Schema(description = "Активна ли учетная запись пациента")
    Boolean isActive;
}
