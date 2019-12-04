package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.dto.ReviewDTO;
import com.bmeonlab.valki.webshop.model.Review;
import com.bmeonlab.valki.webshop.service.ReviewService;
import com.bmeonlab.valki.webshop.utils.DTOConverter;
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

    @Autowired
    private DTOConverter dtoConverter;

    @GetMapping(value = "{id}")
    public ReviewDTO getReviewById(@PathVariable Long id) {
        Review review = reviewService.getReviewById(id);
        return dtoConverter.toReviewDTO(review);
    }

    @PostMapping
    public ReviewDTO createReview(@Valid @NotNull @RequestBody ReviewDTO reviewDTO) {
        Review review = dtoConverter.toReview(reviewDTO);
        Review createdReview = reviewService.createReview(review);
        return dtoConverter.toReviewDTO(createdReview);
    }

    @PutMapping(value = "{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @Valid @NotNull @RequestBody ReviewDTO reviewDTO) {
        Review review = dtoConverter.toReview(reviewDTO);
        Review updatedReview = reviewService.updateReview(id, review);
        return dtoConverter.toReviewDTO(updatedReview);
    }

    @DeleteMapping(value = "{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
