package com.ali.nurse_at_home.controller.v1;

import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PatientController {

    PatientService patientService;

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody PatientParams params) {
        return ok(patientService.create(params));
    }

    @GetMapping("/{id}/full")
    public ResponseEntity<PatientFullDto> getFullPatientById(@PathVariable long id) {
        return ok(patientService.getFullById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientExtendedDto> getExtendedPatientById(@PathVariable long id) {
        return ok(patientService.getExtendedById(id));
    }

//    добавить спецификации
    @GetMapping
    public ResponseEntity<Page<PatientThinDto>> getAllPatients(Pageable pageable) {
        return ok(patientService.getAll(pageable));
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
