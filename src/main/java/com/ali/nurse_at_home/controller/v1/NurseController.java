package com.ali.nurse_at_home.controller.v1;

import com.ali.nurse_at_home.model.dto.nurse.NurseExtendedDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseFullDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseThinDto;
import com.ali.nurse_at_home.model.params.NurseParams;
import com.ali.nurse_at_home.model.params.update.NurseUpdateParams;
import com.ali.nurse_at_home.service.NurseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/nurses")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class NurseController {

    NurseService nurseService;

    //Создать медсестру
    @PostMapping
//    @CheckPermission(roles = {SUPER_ADMIN, NURSE})
    public ResponseEntity<NurseFullDto> create(@RequestBody NurseParams params) {
        return status(CREATED).body(nurseService.create(params));
    }

    //Обновить медсестру
    @PatchMapping("/{id}")
//    @CheckPermission(roles = {SUPER_ADMIN, NURSE})
    public ResponseEntity<NurseFullDto> updateById(@PathVariable long id,
                                                   @RequestBody @Valid NurseUpdateParams params) {
        return ok(nurseService.updateById(id, params));
    }

    //Получить список медсестер, которые уже оказывали услуги (для пациента, сокращенная информация)
    @GetMapping("/from-done-bids")
//    @CheckPermission(roles = {SUPER_ADMIN, PATIENT})
    public ResponseEntity<Page<NurseThinDto>> getFromDoneBids(@SortDefault(sort = {"lastname", "firstName"})
                                                              Pageable pageable) {
        return ok(nurseService.getFromDoneBids(pageable));
    }

    //Получить черный список медсестер (для пациента, сокращенная информация)
    @GetMapping("/blacklist")
//    @CheckPermission(roles = {SUPER_ADMIN, PATIENT})
    public ResponseEntity<Page<NurseThinDto>> getBlacklist(@SortDefault(sort = {"lastname", "firstName"})
                                                           Pageable pageable) {
        return ok(nurseService.getBlacklist(pageable));
    }

    //Добавить медсестру в черный список (для пациента)
    @PostMapping("/{id}/blacklist")
//    @CheckPermission(roles = {SUPER_ADMIN, PATIENT})
    public ResponseEntity<Page<NurseThinDto>> addNurseToBlacklist(@PathVariable long id) {
        nurseService.addNurseToBlacklist(id);
        return ok().build();
    }

    //Удалить медсестру из черного списка (для пациента)
    @DeleteMapping("/{id}/blacklist")
//    @CheckPermission(roles = {SUPER_ADMIN, PATIENT})
    public ResponseEntity<Page<NurseThinDto>> removeNurseFromBlacklist(@PathVariable long id,
                                                                       @SortDefault(sort = {"lastname", "firstName"})
                                                                       Pageable pageable) {
        return ok(nurseService.removeNurseFromBlacklist(id, pageable));
    }

    //Получить медсестру (для медсестры. Полная информация)
    @GetMapping("/my-account")
//    @CheckPermission(roles = {NURSE})
    public ResponseEntity<NurseFullDto> getByToken() {
        return ok(nurseService.getByToken());
    }

    @GetMapping("/{id}/full")
//    @CheckPermission(roles = {SUPER_ADMIN, NURSE})
    public ResponseEntity<NurseFullDto> getFullById(@PathVariable long id) {
        return ok(nurseService.getFullById(id));
    }

    //получить медсестру (для пациента. Расширенная информация)
    @GetMapping("/{id}")
//    @CheckPermission(roles = {SUPER_ADMIN, PATIENT})
    public ResponseEntity<NurseExtendedDto> getExtendedById(@PathVariable long id) {
        return ok(nurseService.getExtendedById(id));
    }

    @DeleteMapping("/{id}")
//    @CheckPermission(roles = {SUPER_ADMIN, NURSE})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        nurseService.deleteById(id);
        return ok().build();
    }
}
