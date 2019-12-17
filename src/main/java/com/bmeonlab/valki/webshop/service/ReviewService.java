package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.model.Review;
import com.bmeonlab.valki.webshop.repository.ReviewRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import com.bmeonlab.valki.webshop.utils.exceptions.UnauthenticatedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Review createReview(Review review) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(username == null || username.equals("anonymousUser"))
            throw new UnauthenticatedUserException("Unauthenticated user can not review products.");
        Customer customer = customerService.getCustomerByUsername(username);

        review.setCustomer(customer);

        return reviewRepository.saveAndFlush(review);
    }

    public Review getReviewById(Long id) { return reviewRepository.getOne(id); }

    public List<Review> getReviewsByProductId(Long productId) { return reviewRepository.findByProductId(productId); }

    @Transactional
    public Review updateReview(Long id, Review review) {
        Review existingReview = reviewRepository.findById(id).orElse(new Review());
        NullAwareBeanUtils.copyNonNullProperties(review, existingReview);
        return reviewRepository.saveAndFlush(existingReview);
    }

    @Transactional
    public void deleteReview(Long id) { reviewRepository.deleteById(id); }
}
