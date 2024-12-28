package com.ali.nurse_at_home.repository;

import com.ali.nurse_at_home.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
