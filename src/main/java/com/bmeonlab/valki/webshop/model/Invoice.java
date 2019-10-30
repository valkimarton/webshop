package com.bmeonlab.valki.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "invoice")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "customer may not be null")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "invoice")        // TODO: no 'invoice' in Product Entity. Review when needed
    private List<ProductInCart> products;

    public Invoice(){}

    public Invoice(@NotNull(message = "customer may not be null") Customer customer, List<ProductInCart> products) {
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

    public Long getId() {
        return id;
    }
}
