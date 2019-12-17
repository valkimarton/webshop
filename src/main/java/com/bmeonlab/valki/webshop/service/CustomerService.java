package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Cart;
import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.model.Role;
import com.bmeonlab.valki.webshop.model.enums.RoleType;
import com.bmeonlab.valki.webshop.repository.CartRepository;
import com.bmeonlab.valki.webshop.repository.CustomerRepository;
import com.bmeonlab.valki.webshop.repository.RoleRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import com.bmeonlab.valki.webshop.utils.exceptions.ActionNotAllowedException;
import com.bmeonlab.valki.webshop.utils.exceptions.UnauthenticatedUserException;
import com.bmeonlab.valki.webshop.utils.exceptions.WebshopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CartRepository cartRepository;

    @Transactional(rollbackFor = WebshopException.class)
    public Customer createCustomer(Customer customer) throws WebshopException {
        Cart cart = new Cart(customer, null);
        customer.setCart(cart);

        Role userRole = roleRepository.findByName(RoleType.USER).orElse(null);
        if (userRole == null)
            throw new WebshopException("USER role doesn't exist");

        customer.setRoles(new ArrayList<Role>());
        customer.addRole(userRole);
        customer.setEnabled(true);

        cartRepository.saveAndFlush(cart);
        return customerRepository.saveAndFlush(customer);

    }

    public Customer getCustomerById(Long id){
        return customerRepository.getOne(id);
    }

    public Customer getCustomerByUsername(String username){
        return customerRepository.findByUsername(username).orElse(null);
    }

    public Customer getRequesterCustomer() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(username == null || username.equals("anonymousUser"))
            throw new UnauthenticatedUserException("unauthenticated user can not view his/her profile");

        Customer customer = getCustomerByUsername(username);
        return customer;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    @Transactional
    public Customer updateCustomer(Long id, Customer customer){
        Customer existingCustomer = customerRepository.findById(id).orElse(new Customer());
        NullAwareBeanUtils.copyNonNullProperties(customer, existingCustomer);
        return customerRepository.saveAndFlush(existingCustomer);
    }

    @Transactional
    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);      // TODO: adja esetleg vissza a törölt Customert?
    }
}
