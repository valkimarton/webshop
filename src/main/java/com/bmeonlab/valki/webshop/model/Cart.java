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
    @OneToOne       // TODO OneToOne szintaktikáját, működését megnézni.
    private Customer customer;

    @ManyToMany     // TODO: Product-oknál nem akarom számontartani, hogy milyen Cart-okban van benne. Akkor nem lehet ManyToOne?   TODO: ManyToMany működését megnézni
    private List<Product> products;



    protected Cart() {}

    public Cart(@NotNull(message = "customer may not be null") Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }
}
