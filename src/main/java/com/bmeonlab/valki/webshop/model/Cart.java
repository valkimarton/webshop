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
    @OneToOne(mappedBy = "cart")     // TODO:   Ha sűrűn használom ezt az tagváltozót, akkor mappedBy helyett megéri ide is felvenni egy JoinColumn-t?
    private Customer customer;

    @ManyToMany     // TODO: ManyToMany működését megnézni
    @JoinTable(name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_iddd", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_idd", referencedColumnName = "id"))
    private List<ProductInCart> products;



    protected Cart() {}

    public Cart(@NotNull(message = "customer may not be null") Customer customer, List<ProductInCart> products) {
        this.customer = customer;
        this.products = products;
    }
}
