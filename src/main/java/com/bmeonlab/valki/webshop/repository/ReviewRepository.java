package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
