package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.model.Review;
import com.bmeonlab.valki.webshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/v1/review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping(value = "{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PostMapping
    public Review createReview(@Valid @NotNull @RequestBody Review review) {
        return reviewService.createReview(review);
    }

    @PutMapping(value = "{id}")
    public Review updateReview(@PathVariable Long id, @Valid @NotNull @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    @DeleteMapping(value = "{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
