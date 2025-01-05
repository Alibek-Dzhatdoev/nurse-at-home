package com.nurseathome.bid.service;

import com.nurseathome.bid.model.dto.BidDto;
import com.nurseathome.bid.model.params.BidParams;

public interface BidService {

    BidDto create(BidParams params);


}
