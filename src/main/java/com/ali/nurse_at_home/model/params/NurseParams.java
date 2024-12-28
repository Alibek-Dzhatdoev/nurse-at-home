package com.ali.nurse_at_home.model.params;

import com.ali.nurse_at_home.model.enums.SearchRadius;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель для создания медсестры")
public class NurseParams {

    @NotBlank(message = "Укажите имя")
    @Schema(description = "Имя медсестры", example = "Лариса")
    @Size(min = 2, max = 15, message = "Имя должно быть от 2 до 15 символов")
    String firstname;

    @NotBlank(message = "Укажите фамилию")
    @Schema(description = "фамилия медсестры", example = "Ивановна")
    @Size(min = 2, max = 20, message = "Фамилия должна быть от 2 до 20 символов")
    String lastname;

    @Schema(description = "Адрес, от которого будет производиться поиск пациентов")
    @NotNull(message = "Укажите адрес")
    AddressParams address;

    @Schema(description = "Радиус поиска пациентов",
            allowableValues = {"ONE", "THREE", "FIVE", "TEN"})
    @NotNull(message = "Выберите радиус поиска пациентов")
    SearchRadius searchRadius;

    @Schema(description = "Список ID услуг(процедур), которые медсестра согласна исполнять (может изменить позже)")
    List<Long> procedureIds;
}
