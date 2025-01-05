package com.nurseathome.bid.repository;

import com.nurseathome.bid.model.entity.PatientAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientAddressRepository extends JpaRepository<PatientAddress, Long> {

    Optional<PatientAddress> findByPatientIdAndAddressId(Long patientId, Long addressId);
}
