package com.bmeonlab.valki.webshop.repository;

import com.bmeonlab.valki.webshop.model.Role;
import com.bmeonlab.valki.webshop.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType roleName);
}
