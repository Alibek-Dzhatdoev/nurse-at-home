package com.ali.nurse_at_home.model.dto.nurse;

import com.ali.nurse_at_home.model.dto.AddressDto;
import com.ali.nurse_at_home.model.enums.SearchRadius;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static lombok.AccessLevel.PRIVATE;

@Data
@JsonInclude(NON_NULL)
@FieldDefaults(level = PRIVATE)
@Schema(description = "Полная модель данных о медсестре (для медсестры)")
public class NurseFullDto {

    @Schema(description = "ID медсестры")
    Long id;

    @Schema(description = "Имя медсестры")
    String firstname;

    @Schema(description = "Фамилия медсестры")
    String lastname;

    @Schema(description = "URL на фотографию (аватарки)")
    String photoUrl;

    @Schema(description = "URL на фото диплома")
    String diplomaUrl;

    @Schema(description = "Адрес, взятый за отправную точку для поиска пациентов")
    AddressDto address;

    @Schema(description = "Радиус поиска пациентов медсестры")
    SearchRadius searchRadius;

    @Schema(description = "Доступность медсестры (работает ли сейчас)")
    Boolean isAvailable;

    @Schema(description = "Рейтинг медсестры (до 5)")
    Double rating;
}
