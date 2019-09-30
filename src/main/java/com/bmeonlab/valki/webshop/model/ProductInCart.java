package com.bmeonlab.valki.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    @JsonIgnore
    private Voucher voucher;

    public ProductInCart() {}

    public ProductInCart(
            @NotNull(message = "product may not be null") Product product,
            @NotNull(message = "number of products may not be null") int numberOfProducts,
            @NotNull(message = "purchase price may not be null") int purchasePrice,
            Cart cart,
            Voucher voucher) {
        this.product = product;
        NumberOfProducts = numberOfProducts;
        this.purchasePrice = purchasePrice;
        this.cart = cart;
        this.voucher = voucher;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumberOfProducts() {
        return NumberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        NumberOfProducts = numberOfProducts;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public Long getId() {
        return id;
    }
}
