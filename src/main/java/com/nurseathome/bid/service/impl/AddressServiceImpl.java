package com.nurseathome.bid.service.impl;

import com.nurseathome.bid.client.GeoClient;
import com.nurseathome.bid.model.entity.address.*;
import com.nurseathome.bid.model.params.AddressParams;
import com.nurseathome.bid.repository.address.*;
import com.nurseathome.bid.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class AddressServiceImpl implements AddressService {

    GeoClient geoClient;

    AddressRepository addressRepository;
    CountryRepository countryRepository;
    ProvinceRepository provinceRepository;
    AreaRepository areaRepository;
    LocalityRepository localityRepository;

    @Override
    @Transactional
    public Address checkAddressAndReturn(AddressParams params) {
        val location = geoClient.getLocation(params.getGeoUrl());

        val address = addressRepository.find(
                params.getCountry(),
                params.getProvince(),
                params.getArea(),
                params.getLocality(),
                params.getStreet(),
                params.getHouse(),
                params.getEntrance(),
                params.getApartment(),
                params.getFloor(),
                params.getIntercom(),
                location.getLatitude(),
                location.getLongitude());

        return address.orElseGet(() -> {
            val country = countryRepository.findByName(params.getCountry())
                    .orElseGet(() -> new Country().setName(params.getCountry()));
            val province = provinceRepository.findByName(params.getProvince())
                    .orElseGet(() -> new Province().setName(params.getProvince()));
            val area = areaRepository.findByName(params.getArea())
                    .orElseGet(() -> new Area().setName(params.getArea()));
            val locality = localityRepository.findByName(params.getLocality())
                    .orElseGet(() -> new Locality().setName(params.getLocality()));
            return addressRepository.save(new Address().setCountry(country)
                    .setProvince(province)
                    .setArea(area)
                    .setLocality(locality)
                    .setStreet(params.getStreet())
                    .setHouse(params.getHouse())
                    .setEntrance(params.getEntrance())
                    .setApartment(params.getApartment())
                    .setFloor(params.getFloor())
                    .setIntercom(params.getIntercom())
                    .setGeoUrl(params.getGeoUrl())
                    .setLatitude(location.getLatitude())
                    .setLongitude(location.getLongitude()));
        });
    }
}
