package com.nurseathome.bid.controller.v1;

import com.nurseathome.bid.controller.v1.docs.PatientControllerDocs;
import com.nurseathome.bid.model.dto.patient.PatientExtendedDto;
import com.nurseathome.bid.model.dto.patient.PatientFullDto;
import com.nurseathome.bid.model.dto.patient.PatientThinDto;
import com.nurseathome.bid.model.entity.Patient;
import com.nurseathome.bid.model.params.PatientParams;
import com.nurseathome.bid.model.params.update.PatientUpdateParams;
import com.nurseathome.bid.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
@RequestMapping("/api/v1/patients")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PatientController implements PatientControllerDocs {

    PatientService patientService;

    @Override
    @PostMapping
    //    @CheckPermission(roles = {SERVICE})
    public ResponseEntity<PatientFullDto> create(@RequestBody @Valid PatientParams params) {
        return status(CREATED).body(patientService.create(params));
    }

    @Override
    @GetMapping("/{id}/full")
    //    @CheckPermission(roles = {ADMIN})
    public ResponseEntity<PatientFullDto> getFullById(@PathVariable long id) {
        return ok(patientService.getFullById(id));
    }

    @Override
    @GetMapping("/my-account")
    //    @CheckPermission(roles = {PATIENT})
    public ResponseEntity<PatientFullDto> getFullByToken() {
        return ok(patientService.getFullByToken());
    }

    @Override
    @GetMapping("/{id}")
    //    @CheckPermission(roles = {ADMIN, NURSE})
    public ResponseEntity<PatientExtendedDto> getExtendedById(@PathVariable long id) {
        return ok(patientService.getExtendedById(id));
    }

    @Override
    @GetMapping
    //    @CheckPermission(roles = {ADMIN})
    public ResponseEntity<Page<PatientThinDto>> getAll(
            @And({
                    @Spec(path = "mobilePhone", params = "mobilePhone", spec = Equal.class),
                    @Spec(path = "firstname", params = "firstname", spec = LikeIgnoreCase.class),
                    @Spec(path = "lastname", params = "lastname", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
                    @Spec(path = "isActive", params = "isActive", spec = Equal.class),
            }) Specification<Patient> patientSpec,
            Pageable pageable) {
        return ok(patientService.getAll(patientSpec, pageable));
    }

    @Override
    @PatchMapping("/{id}")
    //    @CheckPermission(roles = ADMIN)
    public ResponseEntity<PatientFullDto> updateById(@PathVariable long id,
                                                     @RequestBody @Valid PatientUpdateParams params) {
        return ok(patientService.updateById(id, params));
    }

    @PatchMapping("/is-active")
    //    @CheckPermission(roles = SERVICE)
    public ResponseEntity<Void> setActive(@RequestParam UUID ssoUserId,
                                          @RequestParam boolean isActive) {
        patientService.setIsActive(ssoUserId, isActive);
        return noContent().build();
    }

    @Override
    @PatchMapping
    //    @CheckPermission(roles = PATIENT)
    public ResponseEntity<PatientFullDto> updateByToken(@RequestBody @Valid PatientUpdateParams params) {
        return ok(patientService.updateByToken(params));
    }

    //Получить черный список пациентов (для медсестры)
    @Override
    //    @CheckPermission(roles = {NURSE})
    @GetMapping("/blacklist")
    public ResponseEntity<Page<PatientThinDto>> getBlacklist(@SortDefault(sort = {"lastname", "firstName"})
                                                             Pageable pageable) {
        return ok(patientService.getBlackList(pageable));
    }

    //Добавить пациента в черный список (для медсестры)
    @Override
    //    @CheckPermission(roles = {NURSE})
    @PostMapping("/{id}/blacklist")
    public ResponseEntity<Void> addToBlacklist(@PathVariable long id) {
        patientService.addToBlacklist(id);
        return ok().build();
    }

    //Удалить пациента из черного списка (для медсестры)
    @Override
    //    @CheckPermission(roles = {NURSE})
    @DeleteMapping("/{id}/blacklist")
    public ResponseEntity<Page<PatientThinDto>> removeFromBlacklist(@PathVariable long id,
                                                                    @SortDefault(sort = {"lastname", "firstName"})
                                                                    Pageable pageable) {
        return ok(patientService.removeFromBlacklist(id, pageable));
    }
}
