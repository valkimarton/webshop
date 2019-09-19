package com.bmeonlab.valki.webshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_in_cart")
public class ProductInCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "product may not be null")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @NotNull(message = "number of products may not be null")
    private int NumberOfProducts;

    @NotNull(message = "purchase price may not be null")
    private int purchasePrice;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;
}
