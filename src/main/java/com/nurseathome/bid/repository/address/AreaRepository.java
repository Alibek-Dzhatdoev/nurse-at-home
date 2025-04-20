package com.nurseathome.bid.repository.address;

import com.nurseathome.bid.model.entity.address.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaRepository extends JpaRepository<Area, Long> {

    Optional<Area> findByName(String name);
}
