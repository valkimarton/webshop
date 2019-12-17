package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.*;
import com.bmeonlab.valki.webshop.model.enums.RoleType;
import com.bmeonlab.valki.webshop.repository.ProductInCartRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import com.bmeonlab.valki.webshop.utils.exceptions.ActionNotAllowedException;
import com.bmeonlab.valki.webshop.utils.exceptions.IllegalDeleteRequestException;
import com.bmeonlab.valki.webshop.utils.exceptions.UnauthenticatedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductInCartService {

    @Autowired
    private ProductInCartRepository productInCartRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InvoiceService invoiceService;

    @Transactional
    public ProductInCart createProductInCart(ProductInCart productInCart) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(username == null || username.equals("anonymousUser"))
            throw new UnauthenticatedUserException("Unauthenticated user can not add item to cart.");

        Customer customer = customerService.getCustomerByUsername(username);
        Cart cart = customer.getCart();

        productInCart.setCart(cart);

        return productInCartRepository.saveAndFlush(productInCart);
    }

    public List<ProductInCart> getProductInCartsByUserId(Long id) {
        Customer customer = customerService.getCustomerById(id);
        return customer.getCart().getProducts();
    }

    public ProductInCart getProductInCartById(Long id) { return productInCartRepository.getOne(id); }

    public List<ProductInCart> getProductInCarts() { return productInCartRepository.findAll(); }

    public List<ProductInCart> getProductInCartsByInvoiceId(Long invoiceId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(username == null || username.equals("anonymousUser"))
            throw new UnauthenticatedUserException("Unauthenticated user can not request invoice products.");

        Customer customer = customerService.getCustomerByUsername(username);

        if(customer.getRoles().contains(RoleType.ADMIN) == false) {
            if(invoiceService.getInvoiceById(invoiceId).getCustomer().getUsername().equals(username) == false)
                throw new UnauthenticatedUserException("User can not access products from other users' invoice.");
        }

        return productInCartRepository.findByInvoiceId(invoiceId).orElse(new ArrayList<>());

    }

    @Transactional
    public ProductInCart updateProductInCart(Long id, ProductInCart productInCart) {
        ProductInCart existingProductInCart = productInCartRepository.findById(id).orElse(new ProductInCart());
        NullAwareBeanUtils.copyNonNullProperties(productInCart, existingProductInCart);
        existingProductInCart.setCart(productInCart.getCart());
        return productInCartRepository.saveAndFlush(existingProductInCart);
    }

    @Transactional
    public void deleteProductInCart(Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(username == null || username.equals("anonymousUser"))
            throw new UnauthenticatedUserException("Unauthenticated user can not remove an item from a cart.");

        if(getProductInCartById(id).getInvoice() != null)
            throw new IllegalDeleteRequestException("Can not remove a ProductInCart entity, if it is already sold");

        Customer customer = customerService.getCustomerByUsername(username);

        if(getProductInCartById(id) != null) {
            if (customer.getCart().getProducts().contains(getProductInCartById(id)) == false)
                throw new ActionNotAllowedException("User can not remove a product, which is not in his/her cart");
        }

        productInCartRepository.deleteById(id);
    }

    @Transactional
    public List<ProductInCart> buyProductInCarts() {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(username == null || username.equals("anonymousUser"))
            throw new UnauthenticatedUserException("Unauthenticated user can not add item to cart.");

        Customer customer = customerService.getCustomerByUsername(username);
        List<ProductInCart> productInCarts = customer.getCart().getProducts();

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setProducts(new ArrayList<>());
        Invoice createdInvoice = invoiceService.createInvoice(invoice);

        for (ProductInCart productInCart : productInCarts) {
            productInCart.setCart(null);
            productInCart.setInvoice(createdInvoice);
            updateProductInCart(productInCart.getId(), productInCart);

        }

        return productInCarts;
    }
}
