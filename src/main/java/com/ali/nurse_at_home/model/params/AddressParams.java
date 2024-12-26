package com.ali.nurse_at_home.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель для создания адреса")
public class AddressParams {

    @Schema(description = "ID города", example = "1")
    @NotNull(message = "Укажите ID города")
    Long cityId;

    @Schema(description = "ID улицы", example = "1")
    @NotNull(message = "Укажите ID улицы")
    Long streetId;

    @Schema(description = "Номер дома", example = "45/5")
    @NotNull(message = "Укажите номер дома")
    String building;

    @Schema(description = "Номер подъезда", example = "1")
    int entrance;

    @Schema(description = "Номер квартиры", example = "21")
    int apartment;
}
