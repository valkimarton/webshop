package com.bmeonlab.valki.webshop.dto;

import java.util.List;

public class CartDTO {
    private Long id;
    private Long customerId;
    private List<Long> productInCartIds;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getProductInCartIds() {
        return productInCartIds;
    }

    public void setProductInCartIds(List<Long> productInCartIds) {
        this.productInCartIds = productInCartIds;
    }
}
