package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Review;
import com.bmeonlab.valki.webshop.repository.ReviewRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review createReview(Review review) { return reviewRepository.saveAndFlush(review); }

    public Review getReviewById(Long id) { return reviewRepository.getOne(id); }

    public List<Review> getReviewsByProductId(Long productId) { return reviewRepository.findByProductId(productId); }

    public Review updateReview(Long id, Review review) {
        Review existingReview = reviewRepository.findById(id).orElse(new Review());
        BeanUtils.copyProperties(review, existingReview);
        return reviewRepository.saveAndFlush(existingReview);
    }

    public void deleteReview(Long id) { reviewRepository.deleteById(id); }
}
