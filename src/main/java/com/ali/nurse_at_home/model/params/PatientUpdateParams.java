package com.ali.nurse_at_home.model.params;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;
import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(level = PRIVATE)
@Schema(description = "Модель для создания пациента")
public class PatientUpdateParams {

    @Schema(description = "Имя пациента", example = "Валерия")
    @Size(min = 2, max = 15, message = "Имя должно быть от 2 до 15 символов")
    String firstName;

    @Schema(description = "Фамилия пациента", example = "Кузнецова")
    @Size(min = 2, max = 20, message = "Фамилия должна быть от 2 до 20 символов")
    String lastName;

    @Schema(description = "Номер телефона", example = "9094957899")
    @Pattern(regexp = "^9\\d{9}$", message = "Номер телефона не соответствует шаблону")
    String mobilePhone;

    @Schema(description = "Электронная почта", example = "user@gmail.com")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Электронная почта не соответствует шаблону")
    String email;

    @Schema(description = "Дата рождения", example = "1995-06-07")
    @JsonFormat(shape = STRING, timezone = "UTC",
            pattern = "yyyy-MM-dd")
    LocalDate dateOfBirth;

    @Schema(description = "Основной адрес")
    AddressParams address;
}
