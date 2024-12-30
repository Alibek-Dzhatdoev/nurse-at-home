package com.ali.nurse_at_home.controller.v1;

import com.ali.nurse_at_home.aspect.CheckPermission;
import com.ali.nurse_at_home.model.dto.ProcedureDto;
import com.ali.nurse_at_home.model.dto.ProcedureThinDto;
import com.ali.nurse_at_home.model.entity.Procedure;
import com.ali.nurse_at_home.model.params.ProcedureParams;
import com.ali.nurse_at_home.service.ProcedureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ali.nurse_at_home.model.enums.Role.SUPER_ADMIN;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/procedures")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProcedureController {

    ProcedureService procedureService;

    //добавить услугу
    @PostMapping
    @CheckPermission(roles = SUPER_ADMIN)
    public ResponseEntity<ProcedureDto> create(@RequestBody @Valid ProcedureParams params) {
        return status(CREATED).body(procedureService.create(params));
    }

    //удалить услугу
    @DeleteMapping("/{id}")
    @CheckPermission(roles = SUPER_ADMIN)
    public ResponseEntity<Void> delete(@PathVariable long id) {
        procedureService.deleteById(id);
        return ok().build();
    }

    //обновить услугу
    @PatchMapping("/{id}")
    @CheckPermission(roles = SUPER_ADMIN)
    public ResponseEntity<ProcedureDto> update(@PathVariable long id,
                                    @RequestBody ProcedureParams params) {
        return ok(procedureService.update(id, params));
    }

    //получить инфу по услуге
    @GetMapping("/{id}")
    public ResponseEntity<ProcedureDto> getById(@PathVariable long id) {
        return ok(procedureService.getById(id));
    }

    //получить страницу услуг (неактивных тоже)
    @GetMapping("/all")
    @CheckPermission(roles = SUPER_ADMIN)
    public ResponseEntity<Page<ProcedureThinDto>> getAllWithInactiveOnes(
            @And({
                    @Spec(path = "price", params = {"priceFrom", "priceTo"}, spec = Between.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "isActive", params = "isActive", spec = Equal.class),
            })
            Specification<Procedure> specification, Pageable pageable) {
        return ok(procedureService.getAllWithInactiveOnes(specification, pageable));
    }

    @GetMapping
    public ResponseEntity<Page<ProcedureThinDto>> getAll(
            @And({
                    @Spec(path = "price", params = {"priceFrom", "priceTo"}, spec = Between.class),
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
            })
            Specification<Procedure> specification, Pageable pageable) {
        return ok(procedureService.getAll(specification, pageable));
    }
}
