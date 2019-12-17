package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.ProductInCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductInCartRepository extends JpaRepository<ProductInCart, Long> {
    Optional<List<ProductInCart>> findByInvoiceId(Long id);
}
