package com.ali.nurse_at_home.service;

import com.ali.nurse_at_home.model.dto.BidDto;
import com.ali.nurse_at_home.model.params.BidParams;

public interface BidService {

    BidDto create(BidParams params);


}
