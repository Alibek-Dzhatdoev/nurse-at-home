package com.nurseathome.bid.repository;

import com.nurseathome.bid.model.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    @Query("""
           SELECT r
           FROM Review r
           JOIN Bid b ON r.id = b.review.id
           WHERE b.nurseId = :nurseId
           """)
    Page<Review> findAllByNurseId(Long nurseId, Pageable pageable);

    @Query("""
       SELECT COUNT(r)
       FROM Review r
       JOIN Bid b ON r.id = b.review.id
       WHERE b.nurseId = :nurseId
       """)
    long countReviewsByNurseId(Long nurseId);

}
