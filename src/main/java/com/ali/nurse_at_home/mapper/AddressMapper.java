package com.ali.nurse_at_home.mapper;

import com.ali.nurse_at_home.model.dto.AddressDto;
import com.ali.nurse_at_home.model.entity.PatientAddress;
import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.entity.address.City;
import com.ali.nurse_at_home.model.entity.address.Street;
import com.ali.nurse_at_home.model.params.AddressParams;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    @Mapping(target = "city", source = "address.city.name")
    @Mapping(target = "street", source = "address.street.name")
    @Mapping(target = "building", source = "address.building")
    @Mapping(target = "entrance", source = "address.entrance")
    @Mapping(target = "apartment", source = "address.apartment")
    AddressDto toDto(PatientAddress patientAddress);

    List<AddressDto> toDtoList(List<PatientAddress> patientAddresses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    Address toAddress(AddressParams params, City city, Street street);

    @Mapping(target = "city", source = "city.name")
    @Mapping(target = "street", source = "street.name")
    AddressDto toDto(Address address);


    @Named("getPrimaryAddress")
    default AddressDto getPrimaryAddress (List<PatientAddress> addresses) {
        return this.toDto(addresses.stream()
                .filter(PatientAddress::getIsPrimary).findAny().orElse(null));
    }
}
