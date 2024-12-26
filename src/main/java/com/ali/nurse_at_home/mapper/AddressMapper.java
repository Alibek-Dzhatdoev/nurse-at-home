package com.ali.nurse_at_home.mapper;

import com.ali.nurse_at_home.model.dto.AddressDto;
import com.ali.nurse_at_home.model.entity.PatientAddress;
import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.params.AddressParams;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    Address toAddress(AddressParams params);

    @Mapping(target = "city", source = "city.name")
    @Mapping(target = "street", source = "street.name")
    AddressDto toAddressDto(Address address);

    @Mapping(target = "city", source = "address.city.name")
    @Mapping(target = "street", source = "address.street.name")
    @Mapping(target = "building", source = "address.building")
    @Mapping(target = "entrance", source = "address.entrance")
    @Mapping(target = "apartment", source = "address.apartment")
    AddressDto toAddressDto(PatientAddress patientAddress);

    List<AddressDto> toAddressDtoList(List<PatientAddress> patientAddresses);
}
