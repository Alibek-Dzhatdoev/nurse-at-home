package com.nurseathome.bid.repository;

import com.nurseathome.bid.model.entity.address.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street,Long> {
}
