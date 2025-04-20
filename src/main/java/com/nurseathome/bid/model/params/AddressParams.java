package com.nurseathome.bid.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель для создания адреса")
public class AddressParams {

    @Schema(description = "Страна",
            example = "Россия")
    @NotNull(message = "Страна не может быть пустой")
    String country;

    @Schema(description = "Область/республика/АО (необязательно, есть не всегда)",
            example = "Московская область")
    String province;

    @Schema(description = "Административная единица -округ/район (необязательно)",
            example = "район Внуково")
    String area;

    @Schema(description = "Город/поселок/село/деревня и прочие",
            example = "Москва")
    @NotNull(message = "Укажите населенный пункт")
    String locality;

    @Schema(description = "Улица",
            example = "улица Ленина")
    String street;

    @Schema(description = "Дом",
            example = "45с5")
    @NotNull(message = "Укажите дом")
    String house;

    @Schema(description = "Номер подъезда (необязательно), если это частный дом",
            example = "1")
    int entrance;

    @Schema(description = "Номер квартиры (необязательно), если это частный дом",
            example = "21")
    int apartment;

    @Schema(description = "Этаж (необязательно), если это частный дом",
            example = "8")
    int floor;

    @Schema(description = "Домофон (необязательно), если это частный дом",
            example = "21")
    int intercom;

    @Schema(description = "URI для яндекс геокодера")
    String geoUrl;
}
