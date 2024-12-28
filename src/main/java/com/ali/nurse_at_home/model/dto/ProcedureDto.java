package com.ali.nurse_at_home.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель процедуры(услуги)")
public class ProcedureDto {

    @Schema(description = "ID процедуры")
    Long id;

    @Schema(description = "Цена процедуры")
    Double price;

    @Schema(description = "Названия процедуры")
    String name;

    @Schema(description = "Описание процедуры")
    String description;

    @Schema(description = "URL на картинку процедуры")
    String imageUrl;
}
