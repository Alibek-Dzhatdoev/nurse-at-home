package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.address.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
