package com.nurseathome.bid.service.impl;

import com.nurseathome.bid.mapper.ProcedureMapper;
import com.nurseathome.bid.model.dto.ProcedureDto;
import com.nurseathome.bid.model.dto.ProcedureThinDto;
import com.nurseathome.bid.model.entity.Procedure;
import com.nurseathome.bid.model.params.ProcedureParams;
import com.nurseathome.bid.repository.ProcedureRepository;
import com.nurseathome.bid.service.ProcedureService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ProcedureServiceImpl implements ProcedureService {

    ProcedureRepository procedureRepository;
    ProcedureMapper procedureMapper;

    @Override
    public ProcedureDto create(ProcedureParams params) {
        return procedureMapper.toDto(procedureRepository.save(procedureMapper.toProcedure(params)));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        procedureRepository.findById(id).ifPresent(procedure -> {
            procedure.setIsActive(false);
            procedureRepository.save(procedure);
        });
    }

    @Override
    @Transactional
    public ProcedureDto update(long id, ProcedureParams params) {
        return procedureRepository.findById(id)
                .map(procedure -> procedureMapper.update(procedure, params))
                .map(procedureRepository::save)
                .map(procedureMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Процедура не найдена"));
    }

    @Override
    public ProcedureDto getById(long id) {
        return procedureRepository.findById(id)
                .map(procedureMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Процедура не найдена"));
    }

    @Override
    public Page<ProcedureThinDto> getAllWithInactiveOnes(Specification<Procedure> spec,
                                                         Pageable pageable) {
        return procedureRepository.findAll(spec, pageable)
                .map(procedureMapper::toThinDto);
    }

    @Override
    public Page<ProcedureThinDto> getAll(Specification<Procedure> spec, Pageable pageable) {
        val newSpec = spec
                .and((root, query, builder) -> builder.isTrue(root.get("isActive")));

        return getAllWithInactiveOnes(newSpec, pageable);
    }
}
