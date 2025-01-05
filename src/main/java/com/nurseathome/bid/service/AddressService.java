package com.nurseathome.bid.service;

import com.nurseathome.bid.model.entity.address.Address;
import com.nurseathome.bid.model.params.AddressParams;

public interface AddressService {

    Address checkAddressAndReturn(AddressParams params);
}
