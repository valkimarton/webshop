package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.ProductInCart;
import com.bmeonlab.valki.webshop.repository.ProductInCartRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInCartService {

    @Autowired
    private ProductInCartRepository productInCartRepository;

    public ProductInCart createProductInCart(ProductInCart productInCart) { return productInCartRepository.saveAndFlush(productInCart); }

    public ProductInCart getProductInCartById(Long id) { return productInCartRepository.getOne(id); }

    public List<ProductInCart> getProductInCarts() { return productInCartRepository.findAll(); }

    public ProductInCart updateProductInCart(Long id, ProductInCart productInCart) {
        ProductInCart existingProductInCart = productInCartRepository.findById(id).orElse(new ProductInCart());
        NullAwareBeanUtils.copyNonNullProperties(productInCart, existingProductInCart);
        return productInCartRepository.saveAndFlush(existingProductInCart);
    }

    public void deleteProductInCart(Long id) { productInCartRepository.deleteById(id); }
}
