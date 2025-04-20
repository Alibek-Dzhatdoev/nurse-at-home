package com.nurseathome.bid.mapper;

import com.nurseathome.bid.model.dto.AddressDto;
import com.nurseathome.bid.model.entity.PatientAddress;
import com.nurseathome.bid.model.entity.address.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = IGNORE)
public interface AddressMapper {

    List<AddressDto> toDtoList(List<PatientAddress> patientAddresses);

    @Mapping(target = "country", source = "country.name")
    @Mapping(target = "province", source = "province.name")
    @Mapping(target = "area", source = "area.name")
    @Mapping(target = "locality", source = "locality.name")
    AddressDto toDto(Address address);

    default AddressDto toDto(PatientAddress patientAddress) {
        if (patientAddress != null) {
            return this.toDto(patientAddress.getAddress())
                    .setIsPrimary(patientAddress.getIsPrimary());
        }
        return null;
    }
}
