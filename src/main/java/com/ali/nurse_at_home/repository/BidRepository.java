package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("""
           select b from Bid b
           where b.id = :id and b.status = 'DONE'
           """)
    Optional<Bid> findByIdAndStatusIsDone(Long id);
}
