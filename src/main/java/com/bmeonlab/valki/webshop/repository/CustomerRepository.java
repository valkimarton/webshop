package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
