package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByProductId(Long productId);

}
