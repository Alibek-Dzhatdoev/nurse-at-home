package com.nurseathome.bid.controller.v1;

import com.nurseathome.bid.model.params.ReviewParams;
import com.nurseathome.bid.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/reviews")
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class ReviewController {

    ReviewService reviewService;

    //оставить отзыв на полученную услугу (для пациентов)
    @PostMapping
//    @CheckPermission(roles = PATIENT)
    public ResponseEntity<Void> createOrUpdate(@RequestBody @Valid ReviewParams params) {
        reviewService.createOrUpdate(params);
        return ok().build();
    }


    //удалить свой отзыв
    @DeleteMapping("/{id}")
//    @CheckPermission(roles = {PATIENT, SUPER_ADMIN})
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        reviewService.deleteById(id);
        return ok().build();
    }

}
