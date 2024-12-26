package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
