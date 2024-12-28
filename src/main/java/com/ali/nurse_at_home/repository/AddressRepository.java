package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("""
           select a from Address a where
           a.street.id = :streetId
           and a.city.id = :cityId
           and a.building = :building
           and a.entrance = :entrance
           and a.apartment = :apartment
           """)
    Optional<Address> find(Long streetId, Long cityId, String building, int entrance, int apartment);
}
