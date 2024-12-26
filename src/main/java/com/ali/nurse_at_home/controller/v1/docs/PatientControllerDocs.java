package com.ali.nurse_at_home.controller.v1.docs;

import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.params.PatientParams;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "auth-ib-controller", description = "Контроллер для интернет банка")
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
@ApiResponse(responseCode = "404", description = "Not found", content = @Content)
@ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
interface PatientControllerDocs {

    ResponseEntity<?> createPatient(@RequestBody PatientParams params);

    ResponseEntity<PatientFullDto> getFullPatientById(@Parameter long id);

    ResponseEntity<PatientExtendedDto> getExtendedPatientById(@Parameter long id);

    ResponseEntity<Page<PatientThinDto>> getAllPatients(@ParameterObject Pageable pageable);

    ResponseEntity<PatientFullDto> patchPatient(@Parameter long id,
                                                @RequestBody PatientParams params);

    ResponseEntity<Void> deletePatient(@PathVariable long id);
}
