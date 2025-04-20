package com.nurseathome.bid.repository.address;

import com.nurseathome.bid.model.entity.address.Locality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocalityRepository extends JpaRepository<Locality, Long> {

    Optional<Locality> findByName(String name);
}
