package com.ali.nurse_at_home.service;

import com.ali.nurse_at_home.model.dto.nurse.NurseExtendedDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseFullDto;
import com.ali.nurse_at_home.model.dto.nurse.NurseThinDto;
import com.ali.nurse_at_home.model.params.NurseParams;
import com.ali.nurse_at_home.model.params.update.NurseUpdateParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NurseService {

    NurseFullDto create(NurseParams params);

    NurseFullDto updateById(long id, NurseUpdateParams params);

    NurseFullDto getByToken();

    NurseFullDto getFullById(long id);

    NurseExtendedDto getExtendedById(long id);

    void deleteById(long id);

    Page<NurseThinDto> getFromDoneBids(Pageable pageable);

    Page<NurseThinDto> getBlacklist(Pageable pageable);

    void addNurseToBlacklist(long id);

    Page<NurseThinDto> removeNurseFromBlacklist(long id, Pageable pageable);

}
