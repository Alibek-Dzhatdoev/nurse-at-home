package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.address.Street;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StreetRepository extends JpaRepository<Street,Long> {
}
