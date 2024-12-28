package com.ali.nurse_at_home.model.dto.nurse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "расширенная модель данных о медсестре (для пациентов)")
public class NurseExtendedDto {

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

    @Schema(description = "Доступность медсестры (работает ли сейчас)")
    Boolean isAvailable;

    @Schema(description = "Рейтинг медсестры (до 5)")
    Double rating;
}
