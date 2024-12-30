package com.ali.nurse_at_home.service.impl;

import com.ali.nurse_at_home.mapper.ReviewMapper;
import com.ali.nurse_at_home.model.dto.ReviewDto;
import com.ali.nurse_at_home.model.entity.Bid;
import com.ali.nurse_at_home.model.params.ReviewParams;
import com.ali.nurse_at_home.repository.BidRepository;
import com.ali.nurse_at_home.repository.NurseRepository;
import com.ali.nurse_at_home.repository.ReviewRepository;
import com.ali.nurse_at_home.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ReviewServiceImpl implements ReviewService {

    ReviewMapper reviewMapper;

    BidRepository bidRepository;
    NurseRepository nurseRepository;
    ReviewRepository reviewRepository;

    @Override
    @Transactional
    public void create(ReviewParams params) {
        val bidOptional = bidRepository.findByIdAndStatusIsDone(params.getBidId());
        if (bidOptional.isPresent()) {
            Bid bid = bidOptional.get();
            nurseRepository.findById(bid.getNurseId())
                    .ifPresent(nurse -> {
                        val reviewCount = reviewRepository.countReviewsByNurseId(nurse.getId());
                        val allPoints = nurse.getRating() * reviewCount;
                        if (isNull(bid.getReview()))
                            nurse.setRating((allPoints + params.getRate()) / reviewCount + 1);
                        else
                            nurse.setRating((allPoints - bid.getReview().getRate() + params.getRate()) / reviewCount);
                        nurseRepository.save(nurse);
                        val review = reviewMapper.toReview(params);
                        review.setBid(bid);
                        reviewRepository.save(review);
                    });
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Указанная заявка еще не выполнена или не существует");
        }
    }

    @Override
    public Page<ReviewDto> getAll(long nurseId, Pageable pageable) {
        return reviewRepository.findAllByNurseId(nurseId, pageable)
                .map(reviewMapper::toDto);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        reviewRepository.findById(id).ifPresent(review -> {
            val bid = review.getBid();
            reviewRepository.deleteById(id);
            nurseRepository.findById(bid.getNurseId()).ifPresent(nurse -> {
                val reviewCount = reviewRepository.countReviewsByNurseId(nurse.getId());
                nurse.setRating((nurse.getRating() * reviewCount - review.getRate()) / reviewCount - 1);
                nurseRepository.save(nurse);
            });
        });
    }
}
