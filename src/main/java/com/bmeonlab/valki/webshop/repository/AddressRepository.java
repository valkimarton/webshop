package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
