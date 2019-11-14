package com.bmeonlab.valki.webshop.model;

import com.bmeonlab.valki.webshop.model.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "role may not be blank")
    private RoleType name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    List<Customer> customers;

    public Role(@NotNull(message = "role may not be blank") RoleType name) {
        this.name = name;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }
}
