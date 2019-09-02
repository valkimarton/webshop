package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
