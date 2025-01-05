package com.nurseathome.bid.repository;

import com.nurseathome.bid.model.entity.address.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
