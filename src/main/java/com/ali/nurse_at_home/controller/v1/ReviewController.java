package com.ali.nurse_at_home.controller.v1;

import com.ali.nurse_at_home.model.dto.ReviewDto;
import com.ali.nurse_at_home.model.params.ReviewParams;
import com.ali.nurse_at_home.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ReviewController {

    ReviewService reviewService;

    //получить страницу отзывов на медсестру (пагинация и сортировка)
    @GetMapping("/nurses/{nurseId}")
    public ResponseEntity<Page<ReviewDto>> getAllReviews(@PathVariable long nurseId,
                                                         @SortDefault(sort = "date", direction = DESC)
                                                         Pageable pageable) {
        return ok(reviewService.getAll(nurseId, pageable));
    }

    //оставить отзыв на полученную услугу (для пациентов)
    @PostMapping
//    @CheckPermission(roles = PATIENT)
    public ResponseEntity<Void> createReview(@RequestBody @Valid ReviewParams params) {
        reviewService.create(params);
        return ok().build();
    }


    //удалить свой отзыв
    @DeleteMapping("/{id}")
//    @CheckPermission(roles = {PATIENT, SUPER_ADMIN})
    public ResponseEntity<Void> deleteReview(@PathVariable long id) {
        reviewService.deleteById(id);
        return ok().build();
    }

}
