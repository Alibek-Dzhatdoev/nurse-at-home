package com.ali.nurse_at_home.controller.v1.docs;

import com.ali.nurse_at_home.model.dto.nurse.NurseExtendedDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseFullDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseThinDto;
import com.ali.nurse_at_home.model.params.NurseParams;
import com.ali.nurse_at_home.model.params.update.NurseUpdateParams;
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
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "nurse-controller", description = "Контроллер для медсестер")
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found", content = @Content)
@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
public interface NurseControllerDocs {

    @Operation(summary = "Создание медсестры (для медсестры)",
            description = "Метод создания новой медсестры")
    @ApiResponse(responseCode = "201", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NurseFullDto.class)))
    ResponseEntity<NurseFullDto> create(@RequestBody NurseParams params);

    //    @Hidden
    @Operation(summary = "Получить полную информацию медсестры по id (для администратора или внутренней логики)",
            description = "Метод для получения полной информации о медсестре (предназначен для администратора или внутренней логики)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NurseFullDto.class)))
    ResponseEntity<NurseFullDto> getFullById(@Parameter(description = "ID медсестры", example = "1")
                                             long id);

    @Operation(summary = "Получить полную информацию медсестры по токену (для медсестры)",
            description = "Метод для получения полной информации о медсестре (предназначен для медсестры)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NurseFullDto.class)))
    ResponseEntity<NurseFullDto> getFullByToken();

    @Operation(
            summary = "Получить расширенную информацию о медсестре (для медсестры)",
            description = "Метод для получения подробной информации о медсестре (предназначен для медсестер)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NurseExtendedDto.class)))
    ResponseEntity<NurseExtendedDto> getExtendedById(@Parameter(description = "ID медсестры", example = "1")
                                                     long id);

    @Operation(summary = "Получить страницу медсестер (можно по спецификации)",
            description = "Метод для получения списка медсестер (пока непонятно для кого)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = NurseThinDto.class))))
    ResponseEntity<Page<NurseThinDto>> getFromDoneBids(@ParameterObject Pageable pageable);

    @Operation(summary = "Обновить информацию о медсестре (для медсестры)",
            description = "Метод для обновления информации о медсестре (предназначен для медсестер)")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NurseFullDto.class)))
    ResponseEntity<NurseFullDto> updateById(@Parameter long id,
                                            @RequestBody NurseUpdateParams params);

    @Operation(summary = "Удалить медсестру (но оставить запись в БД)",
            description = "Метод помечает медсестры как неактивного")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content)
    ResponseEntity<Void> delete(@Parameter(description = "ID медсестры", example = "1")
                                long id);

    @Operation(summary = "Получить черный список медсестер (для медсестры)",
            description = "Метод для получения черного списка медсестер")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = NurseThinDto.class))))
    ResponseEntity<Page<NurseThinDto>> getBlacklist(@ParameterObject Pageable pageable);

    @Operation(summary = "Добавить медсестру в черный список (для медсестры)",
            description = "Метод для добавления медсестры в черный список, чтобы не видеть заявки от него")
    @ApiResponse(responseCode = "200", description = "Success", content = @Content)
    ResponseEntity<Void> addToBlacklist(@Parameter(description = "ID медсестры", example = "1")
                                        long id);

    @Operation(summary = "Удалить медсестру из черного списка (для медсестры)",
            description = "Метод для удаления медсестры из ЧС, чтобы снова видеть его заявки")
    @ApiResponse(responseCode = "200", description = "Success",
            content = @Content(
                    mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = NurseThinDto.class))))
    ResponseEntity<Page<NurseThinDto>> removeFromBlacklist(@Parameter(description = "ID медсестры", example = "1") long id,
                                                           @ParameterObject Pageable pageable);
}
