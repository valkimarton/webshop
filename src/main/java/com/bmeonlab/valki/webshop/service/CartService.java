package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Cart;
import com.bmeonlab.valki.webshop.repository.CartRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public Cart createCart(Cart cart) { return cartRepository.saveAndFlush(cart); }

    public Cart getCartById(Long id) {
        if (id == null)
            return null;
        return cartRepository.getOne(id);
    }

    public List<Cart> getAllCarts() {return  cartRepository.findAll(); }

    @Transactional
    public Cart updateCart(Long id, Cart cart) {
        Cart existingCart = cartRepository.findById(id).orElse(new Cart());
        NullAwareBeanUtils.copyNonNullProperties(cart, existingCart);
        return cartRepository.saveAndFlush(existingCart);
    }

    @Transactional
    public void deleteCart(Long id) { cartRepository.deleteById(id); }
}
