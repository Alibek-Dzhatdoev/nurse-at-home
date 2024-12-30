package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, Long>, JpaSpecificationExecutor<Patient> {

    @Query("""
           select p from Patient p left join fetch p.addresses where p.id = :id
           """)
    Optional<Patient> findByIdFetchAddresses(Long id);

    Page<Patient> findAllByIdIn(Collection<Long> ids, Pageable pageable);

    Optional<Patient> findByUserId(UUID userId);
}
