package com.ali.nurse_at_home.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель для создания процедуры")
public class ProcedureParams {

    @Schema(description = "Цена процедуры", example = "1500.0")
    @NotNull(message = "Укажите цену для процедуры")
    Double price;

    @Schema(description = "Название процедуры", example = "Установка капельницы")
    @NotNull(message = "Укажите название процедуры")
    String name;

    @Schema(description = "Описание процедуры", example = "Какой-то обширный текст с описание")
    @NotNull(message = "Опишите процедуру")
    String description;

    //TODO урл какого вида долджен быть? наверное урл к сервису файлов
    @Schema(description = "URL для картинки процедуры", example = "Пока непонятно")
//    @NotNull(message = "")
    String imageUrl;
}
