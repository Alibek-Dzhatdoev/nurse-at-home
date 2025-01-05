package com.nurseathome.bid.service;

import com.nurseathome.bid.model.params.ReviewParams;

public interface ReviewService {

    void createOrUpdate(ReviewParams params);

    void deleteById(long id);
}
