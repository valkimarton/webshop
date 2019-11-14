package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.service.CustomerService;
import com.bmeonlab.valki.webshop.utils.exceptions.WebshopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping(value = "{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @GetMapping(value = "username/{username}")
    public Customer getCustomerByUsername(@PathVariable String username) { return customerService.getCustomerByUsername(username); }

    @PostMapping
    public Customer createCustomer(@Valid @NotNull @RequestBody Customer customer) throws WebshopException {
        return customerService.createCustomer(customer);
    }

    @PutMapping(value = "{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
    }
}
