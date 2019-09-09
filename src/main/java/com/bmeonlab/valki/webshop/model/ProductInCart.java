package com.bmeonlab.valki.webshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_in_cart")
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull(message = "product may not be null")
    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @NotNull(message = "number of products may not be null")
    int NumberOfProducts;

    @NotNull(message = "purchase price may not be null")
    int purchasePrice;
}
