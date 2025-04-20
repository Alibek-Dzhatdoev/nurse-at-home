package com.nurseathome.bid.service;

import com.nurseathome.bid.model.dto.nurse.NurseExtendedDto;
import com.nurseathome.bid.model.dto.nurse.NurseFullDto;
import com.nurseathome.bid.model.dto.nurse.NurseThinDto;
import com.nurseathome.bid.model.params.NurseParams;
import com.nurseathome.bid.model.params.update.NurseUpdateParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface NurseService {

    NurseFullDto create(NurseParams params);

    NurseFullDto updateById(long id, NurseUpdateParams params);

    NurseFullDto updateByToken(NurseUpdateParams params);

    NurseFullDto getByToken();

    NurseFullDto getFullById(long id);

    NurseExtendedDto getExtendedById(long id);

    void setIsAvailable(boolean isAvailable);

    void setIsActive(UUID ssoUserId, boolean isActive);

    Page<NurseThinDto> getFromDoneBids(Pageable pageable);

    Page<NurseThinDto> getBlacklist(Pageable pageable);

    void addNurseToBlacklist(long id);

    Page<NurseThinDto> removeNurseFromBlacklist(long id, Pageable pageable);
}
