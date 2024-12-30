package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.mapper.ProcedureMapper;
import com.ali.nurse_at_home.model.dto.ProcedureDto;
import com.ali.nurse_at_home.model.dto.ProcedureThinDto;
import com.ali.nurse_at_home.model.entity.Procedure;
import com.ali.nurse_at_home.model.params.ProcedureParams;
import com.ali.nurse_at_home.repository.ProcedureRepository;
import com.ali.nurse_at_home.service.ProcedureService;
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
