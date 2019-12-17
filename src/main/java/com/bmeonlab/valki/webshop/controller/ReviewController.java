package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.dto.ReviewDTO;
import com.bmeonlab.valki.webshop.model.Review;
import com.bmeonlab.valki.webshop.service.ReviewService;
import com.bmeonlab.valki.webshop.utils.DTOConverter;
import com.bmeonlab.valki.webshop.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private LogUtils logUtils;

    Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @GetMapping(value = "{id}")
    public ReviewDTO getReviewById(@PathVariable Long id, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Review review = reviewService.getReviewById(id);
        return dtoConverter.toReviewDTO(review);
    }

    @PostMapping
    public ReviewDTO createReview(@Valid @NotNull @RequestBody ReviewDTO reviewDTO, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Review review = dtoConverter.toReview(reviewDTO);
        Review createdReview = reviewService.createReview(review);
        return dtoConverter.toReviewDTO(createdReview);
    }

    @PutMapping(value = "{id}")
    public ReviewDTO updateReview(@PathVariable Long id, @Valid @NotNull @RequestBody ReviewDTO reviewDTO, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Review review = dtoConverter.toReview(reviewDTO);
        Review updatedReview = reviewService.updateReview(id, review);
        return dtoConverter.toReviewDTO(updatedReview);
    }

    @DeleteMapping(value = "{id}")
    public void deleteReview(@PathVariable Long id, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        reviewService.deleteReview(id);
    }
}
