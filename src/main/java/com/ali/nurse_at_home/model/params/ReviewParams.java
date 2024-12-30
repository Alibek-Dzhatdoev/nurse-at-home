package com.ali.nurse_at_home.model.params;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель для создания отзыва об оказанной услуге")
public class ReviewParams {

    @NotNull(message = "Укажите ID заявки")
    @Schema(description = "ID выполненной заявки", example = "1")
    Long bidId;

    @Schema(description = "Текст отзыва", example = "Хорошая медсестра")
    String text;

    @NotNull(message = "Укажите оценку")
    @Range(min = 1, max = 5, message = "Оценка должна быть от 1 до 5")
    @Schema(description = "Качество общения", example = "5")
    Integer rate;
}
