package com.nurseathome.bid.repository.address;

import com.nurseathome.bid.model.entity.address.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    Optional<Province> findByName(String name);
}
