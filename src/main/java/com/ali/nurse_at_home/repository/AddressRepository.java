package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
