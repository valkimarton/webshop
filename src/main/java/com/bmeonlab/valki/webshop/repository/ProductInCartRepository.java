package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, Long> {
}
