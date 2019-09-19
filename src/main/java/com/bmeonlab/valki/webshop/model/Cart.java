package com.bmeonlab.valki.webshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "customer may not be null")
    @OneToOne(mappedBy = "cart")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<ProductInCart> products;

    protected Cart() {}

    public Cart(@NotNull(message = "customer may not be null") Customer customer, List<ProductInCart> products) {
        this.customer = customer;
        this.products = products;
    }
}
