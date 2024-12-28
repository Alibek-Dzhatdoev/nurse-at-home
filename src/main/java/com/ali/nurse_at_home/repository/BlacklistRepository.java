package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.NursePatientBlacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<NursePatientBlacklist, Long> {

    @Query("""
           select b from NursePatientBlacklist b where
           b.patientId = :patientId and
           b.nurseId = :nurseId and
           b.initiator = 'PATIENT'
           """)
    Optional<NursePatientBlacklist> findBlackListNurse(Long patientId, Long nurseId);

    @Query("""
           select b from NursePatientBlacklist b where
           b.patientId = :patientId and
           b.nurseId = :nurseId and
           b.initiator = 'NURSE'
           """)
    Optional<NursePatientBlacklist> findBlackListPatient(Long patientId, Long nurseId);
}
