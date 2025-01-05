package com.nurseathome.bid.service;

import com.nurseathome.bid.model.dto.ProcedureDto;
import com.nurseathome.bid.model.dto.ProcedureThinDto;
import com.nurseathome.bid.model.entity.Procedure;
import com.nurseathome.bid.model.params.ProcedureParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProcedureService {

    ProcedureDto create(ProcedureParams params);

    void deleteById(long id);

    ProcedureDto update(long id, ProcedureParams params);

    ProcedureDto getById(long id);

    Page<ProcedureThinDto> getAllWithInactiveOnes(Specification<Procedure> spec, Pageable pageable);

    Page<ProcedureThinDto> getAll(Specification<Procedure> spec, Pageable pageable);
}
