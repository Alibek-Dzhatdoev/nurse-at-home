package com.nurseathome.bid.repository;

import com.nurseathome.bid.model.entity.Bid;
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
