package com.ali.nurse_at_home.controller.v1;

import com.ali.nurse_at_home.aspect.CheckPermission;
import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ali.nurse_at_home.model.enums.Role.SERVICE;
import static com.ali.nurse_at_home.model.enums.Role.SUPER_ADMIN;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PatientController {

    PatientService patientService;

    @PostMapping
    @CheckPermission(roles = {SUPER_ADMIN, SERVICE})
    public ResponseEntity<PatientFullDto> createPatient(@RequestBody PatientParams params) {
        return ok(patientService.create(params));
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<PatientFullDto> getFullPatientById(@PathVariable long id) {
        return ok(patientService.getFullById(id));
    }

    @GetMapping("/me")
    public ResponseEntity<PatientFullDto> getFullPatientByToken() {
        return ok(patientService.getFullByToken());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientExtendedDto> getExtendedPatientById(@PathVariable long id) {
        return ok(patientService.getExtendedById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PatientThinDto>> getAllPatients(
            @And({
                    @Spec(path = "mobilePhone", params = "mobilePhone", spec = Equal.class),
                    @Spec(path = "firstName", params = "firstName", spec = LikeIgnoreCase.class),
                    @Spec(path = "lastName", params = "lastName", spec = LikeIgnoreCase.class),
                    @Spec(path = "email", params = "email", spec = LikeIgnoreCase.class),
            }) Specification<Patient> patientSpec,
            Pageable pageable) {
        return ok(patientService.getAll(patientSpec, pageable));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PatientFullDto> patchPatient(@PathVariable long id,
                                                       @RequestBody PatientParams params) {
        return ok(patientService.patchPatient(id, params));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable long id) {
        patientService.deleteById(id);
        return ok().build();
    }

}
