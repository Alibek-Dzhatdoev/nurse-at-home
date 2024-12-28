package com.ali.nurse_at_home.service;

import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.params.AddressParams;

public interface AddressService {

    Address checkAddressAndReturn(AddressParams params);
}
