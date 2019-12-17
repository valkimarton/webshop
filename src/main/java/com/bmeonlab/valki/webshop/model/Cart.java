package com.bmeonlab.valki.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Long id;

    @NotNull(message = "customer may not be null")
    @OneToOne(mappedBy = "cart", cascade = CascadeType.PERSIST)
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private List<ProductInCart> products;

    public Cart() {}

    public Cart(@NotNull(message = "customer may not be null") Customer customer, List<ProductInCart> products) {
        this.customer = customer;
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(List<ProductInCart> products) {
        this.products = products;
    }

    public void removeProduct(ProductInCart productInCart) {
        products.remove(productInCart);
    }

    public Long getId() {
        return id;
    }
}
