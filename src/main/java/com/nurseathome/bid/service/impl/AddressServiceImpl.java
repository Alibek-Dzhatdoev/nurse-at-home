package com.nurseathome.bid.service.impl;

import com.nurseathome.bid.mapper.AddressMapper;
import com.nurseathome.bid.model.entity.address.Address;
import com.nurseathome.bid.model.params.AddressParams;
import com.nurseathome.bid.repository.AddressRepository;
import com.nurseathome.bid.repository.CityRepository;
import com.nurseathome.bid.repository.StreetRepository;
import com.nurseathome.bid.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AddressServiceImpl implements AddressService {

    AddressMapper addressMapper;

    CityRepository cityRepository;
    StreetRepository streetRepository;
    AddressRepository addressRepository;

    @Override
    public Address checkAddressAndReturn(AddressParams params) {
        val city = cityRepository.findById(params.getCityId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Неверный ID города"));

//        val street = streetRepository.findById(params.getAddress().getStreetId())
        val street = streetRepository.findById(1L)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Неверный ID улицы"));

        street.setCityId(city.getId()); //TODO удалить как только БД с улицами будет

        if (!street.getCityId().equals(city.getId()))
            throw new ResponseStatusException(BAD_REQUEST, "Улица с этим ID не находится в указанном городе");

        return addressRepository.find(street.getId(), city.getId(),
                        params.getBuilding(),
                        params.getEntrance(),
                        params.getApartment())
                .orElseGet(() -> addressRepository.save(addressMapper.toAddress(params, city, street)));
    }
}
