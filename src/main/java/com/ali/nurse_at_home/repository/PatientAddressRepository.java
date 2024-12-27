package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.PatientAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientAddressRepository extends JpaRepository<PatientAddress, Long> {

    Optional<PatientAddress> findByPatientIdAndAddressId(Long patientId, Long addressId);
}
