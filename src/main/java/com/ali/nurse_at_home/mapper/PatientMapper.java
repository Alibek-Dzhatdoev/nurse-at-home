package com.ali.nurse_at_home.mapper;

import com.ali.nurse_at_home.model.dto.AddressDto;
import com.ali.nurse_at_home.model.dto.PatientExtendedDto;
import com.ali.nurse_at_home.model.dto.PatientFullDto;
import com.ali.nurse_at_home.model.dto.PatientThinDto;
import com.ali.nurse_at_home.model.entity.Patient;
import com.ali.nurse_at_home.model.entity.PatientAddress;
import com.ali.nurse_at_home.model.entity.address.Address;
import com.ali.nurse_at_home.model.entity.address.City;
import com.ali.nurse_at_home.model.entity.address.Street;
import com.ali.nurse_at_home.model.params.AddressParams;
import com.ali.nurse_at_home.model.params.PatientParams;
import com.ali.nurse_at_home.model.params.PatientUpdateParams;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface PatientMapper {

    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "userId", source = "userId")
    Patient toPatient(PatientParams params, UUID userId);

    PatientFullDto toFullDto(Patient patient);

    @Mapping(target = "address", source = "addresses", qualifiedByName = "getPrimaryAddress")
    PatientExtendedDto toExtendedDto(Patient patient);

    PatientThinDto toThinDto(Patient patient);

    Patient updatePatient(@MappingTarget Patient patient, PatientUpdateParams params);

    @Named("getPrimaryAddress")
    default AddressDto getPrimaryAddress (List<PatientAddress> addresses) {
        return this.addressToDto(addresses.stream()
                .filter(PatientAddress::isPrimary).findAny().orElse(null));
    }

    @Mapping(target = "city", source = "address.city.name")
    @Mapping(target = "street", source = "address.street.name")
    @Mapping(target = "building", source = "address.building")
    @Mapping(target = "entrance", source = "address.entrance")
    @Mapping(target = "apartment", source = "address.apartment")
    AddressDto addressToDto(PatientAddress patientAddress);

    List<AddressDto> addressListToDtoList(List<PatientAddress> patientAddresses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "city", source = "city")
    @Mapping(target = "street", source = "street")
    Address paramstoAddress(AddressParams params, City city, Street street);

    @Mapping(target = "city", source = "city.name")
    @Mapping(target = "street", source = "street.name")
    AddressDto addressToDto(Address address);
}
