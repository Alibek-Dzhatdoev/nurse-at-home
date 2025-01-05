package com.ali.nurse_at_home.service;

import com.ali.nurse_at_home.model.params.ReviewParams;

public interface ReviewService {

    void createOrUpdate(ReviewParams params);

    void deleteById(long id);
}
