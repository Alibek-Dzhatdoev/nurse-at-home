package com.nurseathome.bid.controller.v1;

import com.nurseathome.bid.controller.v1.docs.NurseControllerDocs;
import com.nurseathome.bid.model.dto.nurse.NurseExtendedDto;
import com.nurseathome.bid.model.dto.nurse.NurseFullDto;
import com.nurseathome.bid.model.dto.nurse.NurseThinDto;
import com.nurseathome.bid.model.params.NurseParams;
import com.nurseathome.bid.model.params.update.NurseUpdateParams;
import com.nurseathome.bid.service.NurseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nurses")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NurseController implements NurseControllerDocs {

    NurseService nurseService;

    //Создать медсестру
    @Override
    @PostMapping
    //    @CheckPermission(roles = {SERVICE})
    public ResponseEntity<NurseFullDto> create(@RequestBody NurseParams params) {
        return status(CREATED).body(nurseService.create(params));
    }

    //Обновить медсестру (для админа)
    @Override
    @PatchMapping("/{id}")
    //    @CheckPermission(roles = {ADMIN})
    public ResponseEntity<NurseFullDto> updateById(@PathVariable long id,
                                                   @RequestBody @Valid NurseUpdateParams params) {
        return ok(nurseService.updateById(id, params));
    }

    @PatchMapping("/is-active")
    //    @CheckPermission(roles = SERVICE)
    public ResponseEntity<Void> setActive(@RequestParam UUID ssoUserId,
                                          @RequestParam boolean isActive) {
        nurseService.setIsActive(ssoUserId, isActive);
        return noContent().build();
    }

    //Обновить медсестру для медсестры
    @Override
    @PatchMapping
    //    @CheckPermission(roles = {NURSE})
    public ResponseEntity<NurseFullDto> updateByToken(@RequestBody @Valid NurseUpdateParams params) {
        return ok(nurseService.updateByToken(params));
    }

    @PatchMapping("/ready-to-work/{isAvailable}")
    //    @CheckPermission(roles = {NURSE})
    public ResponseEntity<Void> setIsAvailable(@PathVariable boolean isAvailable) {
        nurseService.setIsAvailable(isAvailable);
        return noContent().build();
    }

    //Получить список медсестер, которые уже оказывали услуги (для пациента, сокращенная информация)
    @Override
    @GetMapping("/from-bid-history")
    //    @CheckPermission(roles = {ADMIN, PATIENT})
    public ResponseEntity<Page<NurseThinDto>> getFromDoneBids(@SortDefault(sort = {"lastname", "firstName"})
                                                              Pageable pageable) {
        return ok(nurseService.getFromDoneBids(pageable));
    }

    //Получить черный список медсестер (для пациента, сокращенная информация)
    @Override
    @GetMapping("/blacklist")
    //    @CheckPermission(roles = {ADMIN, PATIENT})
    public ResponseEntity<Page<NurseThinDto>> getBlacklist(@SortDefault(sort = {"lastname", "firstName"})
                                                           Pageable pageable) {
        return ok(nurseService.getBlacklist(pageable));
    }

    //Добавить медсестру в черный список (для пациента)
    @Override
    @PostMapping("/{id}/blacklist")
    //    @CheckPermission(roles = {ADMIN, PATIENT})
    public ResponseEntity<Void> addToBlacklist(@PathVariable long id) {
        nurseService.addNurseToBlacklist(id);
        return ok().build();
    }

    //Удалить медсестру из черного списка (для пациента)
    @Override
    @DeleteMapping("/{id}/blacklist")
    //    @CheckPermission(roles = {ADMIN, PATIENT})
    public ResponseEntity<Page<NurseThinDto>> removeFromBlacklist(@PathVariable long id,
                                                                  @SortDefault(sort = {"lastname", "firstName"})
                                                                  Pageable pageable) {
        return ok(nurseService.removeNurseFromBlacklist(id, pageable));
    }

    //Получить медсестру (для медсестры. Полная информация)
    @Override
    @GetMapping("/my-account")
    //    @CheckPermission(roles = {NURSE})
    public ResponseEntity<NurseFullDto> getFullByToken() {
        return ok(nurseService.getByToken());
    }

    @Override
    @GetMapping("/{id}/full")
    //    @CheckPermission(roles = {ADMIN, NURSE})
    public ResponseEntity<NurseFullDto> getFullById(@PathVariable long id) {
        return ok(nurseService.getFullById(id));
    }

    //получить медсестру (для пациента. Расширенная информация)
    @Override
    @GetMapping("/{id}")
    //    @CheckPermission(roles = {ADMIN, PATIENT})
    public ResponseEntity<NurseExtendedDto> getExtendedById(@PathVariable long id) {
        return ok(nurseService.getExtendedById(id));
    }
}
