package com.nurseathome.bid.repository;

import com.nurseathome.bid.model.entity.Nurse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface NurseRepository extends JpaRepository<Nurse, Long> {

    Optional<Nurse> findBySsoUserId(UUID userId);

    Page<Nurse> findAllByIdIn(Collection<Long> ids, Pageable pageable);

    @Query("""
           SELECT DISTINCT n FROM Nurse n
           JOIN Bid b ON b.nurseId = n.id
           WHERE b.patientId = :patientId AND b.status = 'DONE'
           """)
    Page<Nurse> findNursesByPatientIdAndBidIsDone(Long patientId, Pageable pageable);

    @Modifying
    @Query("update Nurse n set n.isAvailable = :isAvailable where n.ssoUserId = :ssoUserId")
    void setIsAvailable(boolean isAvailable, UUID ssoUserId);

    @Modifying
    @Query("update Nurse n set n.isActive = :isActive where n.ssoUserId = :ssoUserId")
    void setIsActive(UUID ssoUserId, boolean isActive);
}
