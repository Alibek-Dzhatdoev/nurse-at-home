package com.nurseathome.bid.repository.address;

import com.nurseathome.bid.model.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("""
           select a from Address a
           where a.country.name = :country
           and (:province = null OR a.province.name = :province)
           and (:area = null OR a.area.name = :area)
           and a.locality.name = :locality
           and (:street = null OR a.street = :street)
           and a.house = :house
           and (:entrance = null OR a.entrance = :entrance)
           and (:apartment = null OR a.apartment = :apartment)
           and (:floor = null OR a.floor = :floor)
           and (:intercom = null OR a.intercom = :intercom)
           and a.latitude = :latitude
           and a.longitude = :longitude
           """)
    Optional<Address> find(String country,
                           String province,
                           String area,
                           String locality,
                           String street,
                           String house,
                           int entrance,
                           int apartment,
                           int floor,
                           int intercom,
                           Double latitude,
                           Double longitude);
}
