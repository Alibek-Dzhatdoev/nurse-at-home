package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.Nurse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findByUserId(UUID userId);

    Page<Nurse> findAllByIdIn(Collection<Long> ids, Pageable pageable);

    @Query("""
            SELECT DISTINCT n FROM Nurse n
            JOIN Bid b ON b.nurseId = n.id
            WHERE b.patientId = :patientId AND b.status = 'DONE'
            """)
    Page<Nurse> findNursesByPatientIdAndBidIsDone(Long patientId, Pageable pageable);
}
