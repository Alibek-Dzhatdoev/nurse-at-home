package com.ali.nurse_at_home.service;

import com.ali.nurse_at_home.model.dto.ReviewDto;
import com.ali.nurse_at_home.model.params.ReviewParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

    void create (ReviewParams params);

    Page<ReviewDto> getAll(long nurseId, Pageable pageable);

    void deleteById(long id);
}
