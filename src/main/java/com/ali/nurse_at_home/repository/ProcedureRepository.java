package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProcedureRepository extends JpaRepository<Procedure, Long>, JpaSpecificationExecutor<Procedure> {
}
