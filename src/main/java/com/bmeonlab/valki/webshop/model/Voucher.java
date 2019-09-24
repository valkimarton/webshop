package com.bmeonlab.valki.webshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "customer may not be null")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "voucher")
    private List<ProductInCart> products;

    public Voucher(){}

    public Voucher(@NotNull(message = "customer may not be null") Customer customer, List<ProductInCart> products) {
        this.customer = customer;
        this.products = products;
    }
}
