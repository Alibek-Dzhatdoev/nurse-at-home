package com.ali.nurse_at_home.model.params;

import com.ali.nurse_at_home.model.enums.TimeIntervals;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель для создания заявки")
public class BidParams {

    @Schema(description = "ID предпочтительной медсестры(необязательно)", example = "1")
    Long nurseId;

    @Schema(description = "Список ID услуг", example = "{1, 2}")
    @NotEmpty(message = "Выберите хотя бы одну процедуру")
    List<Long> serviceIds;

    @Schema(description = "Запланированное время для процедур(необязательно)")
    TimeIntervals scheduledTime;    // если null, то заявка "как можно быстрее"

    @Schema(description = "Запланированная дата для процедур(необязательно)",
            example = "2024-12-25")
    @JsonFormat(shape = STRING, timezone = "UTC", pattern = "yyyy-MM-dd")
    LocalDate scheduledDate;        //если null, то заявка на сегодня

    @Schema(description = "Адрес проведения процедур(если нет - то вызов будет на основной адрес пациента)")
    AddressParams address;          //если null, то заявка на адрес проживания пациента

}
