package com.ali.nurse_at_home.model.dto;

import com.ali.nurse_at_home.model.enums.BidStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель заявки")
public class BidDto {

    @Schema(description = "ID заявки")
    Long id;

    @Schema(description = "Имя медсестры")
    String nurseFirstname;

    @Schema(description = "Фамилия медсестры")
    String nurseLastname;

    @Schema(description = "Имя пациента")
    String patientFirstname;

    @Schema(description = "Фамилия пациента")
    String patientLastname;

    @Schema(description = "Статус заявки")
    BidStatus status;

    @Schema(description = "Список заказанных процедур")
    List<ProcedureDto> services;
}
