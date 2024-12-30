package com.ali.nurse_at_home.controller.v1.docs;

import com.ali.nurse_at_home.model.dto.patient.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.patient.PatientFullDto;
import com.ali.nurse_at_home.model.dto.patient.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.model.params.update.PatientUpdateParams;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import static com.ali.nurse_at_home.utils.constants.Constants.PATIENT_SPEC_EXAMPLE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "patient-controller", description = "Контроллер для пациентов")
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found", content = @Content)
@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
public interface PatientControllerDocs {

    @Operation(summary = "Создание пациента (для пациента)",
            description = "Метод создания нового пациента")
    @ApiResponse(responseCode = "201", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PatientFullDto.class)))
    ResponseEntity<PatientFullDto> createPatient(@RequestBody PatientParams params);

//    @Hidden
    @Operation(summary = "Получить полную информацию пациента по id (для администратора или внутренней логики)",
            description = "Метод для получения полной информации о пациенте (предназначен для администратора или внутренней логики)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PatientFullDto.class)))
    ResponseEntity<PatientFullDto> getFullPatientById(@Parameter(description = "ID пациента", example = "1") long id);

    @Operation(summary = "Получить полную информацию пациента по токену (для пациента)",
            description = "Метод для получения полной информации о пациенте (предназначен для пациента)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PatientFullDto.class)))
    ResponseEntity<PatientFullDto> getFullPatientByToken();

    @Operation(
            summary = "Получить расширенную информацию о пациенте (для медсестры)",
            description = "Метод для получения подробной информации о пациенте (предназначен для медсестер)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PatientExtendedDto.class)))
    ResponseEntity<PatientExtendedDto> getExtendedPatientById(@Parameter(description = "ID пациента", example = "1") long id);

    @Operation(summary = "Получить страницу пациентов (можно по спецификации)",
            description = "Метод для получения списка пациентов (пока непонятно для кого)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PatientThinDto.class))))
    ResponseEntity<Page<PatientThinDto>> getAllPatients(@Parameter(description = "Спецификация для фильтрации пациентов", example = PATIENT_SPEC_EXAMPLE)
                                                        Specification<Patient> patientSpec,
                                                        @ParameterObject Pageable pageable);

    @Operation(summary = "Обновить информацию о пациенте (для пациента)",
            description = "Метод для обновления информации о пациенте (предназначен для пациентов)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PatientFullDto.class)))
    ResponseEntity<PatientFullDto> patchPatient(@Parameter(description = "ID пациента", example = "1") long id,
                                                @RequestBody PatientUpdateParams params);

    @Operation(summary = "Удалить пациента (но оставить запись в БД)",
            description = "Метод помечает пациента как неактивного")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content)
    ResponseEntity<Void> deletePatient(@Parameter(description = "ID пациента", example = "1") long id);

    @Operation(summary = "Получить черный список пациентов (для медсестры)",
            description = "Метод для получения черного списка пациентов")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PatientThinDto.class))))
    ResponseEntity<Page<PatientThinDto>> getBlacklist(@ParameterObject Pageable pageable);

    @Operation(summary = "Добавить пациента в черный список (для медсестры)",
            description = "Метод для добавления пациента в черный список, чтобы не видеть заявки от него")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content)
    ResponseEntity<Void> addToBlacklist(@Parameter(description = "ID пациента", example = "1") long id);

    @Operation(summary = "Удалить пациента из черного списка (для медсестры)",
            description = "Метод для удаления пациента из ЧС, чтобы снова видеть его заявки")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PatientThinDto.class))))
    ResponseEntity<Page<PatientThinDto>> removeFromBlacklist(@Parameter(description = "ID пациента", example = "1") long id,
                                                             @ParameterObject Pageable pageable);
}
