package com.nurseathome.bid.repository;

import com.nurseathome.bid.model.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProcedureRepository extends JpaRepository<Procedure, Long>, JpaSpecificationExecutor<Procedure> {
}
