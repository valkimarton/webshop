package com.bmeonlab.valki.webshop.controller;


import com.bmeonlab.valki.webshop.dto.CustomerDTO;
import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.service.CustomerService;
import com.bmeonlab.valki.webshop.utils.DTOConverter;
import com.bmeonlab.valki.webshop.utils.exceptions.WebshopException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DTOConverter dtoConverter;

    @PostMapping
    public CustomerDTO registerUser(@Valid @NotNull @RequestBody CustomerDTO customerDTO) throws WebshopException {
        Customer customer = dtoConverter.toCustomer(customerDTO);
        Customer createdCustomer = customerService.createCustomer(customer);
        return dtoConverter.toCustomerDTO(createdCustomer);
    }
}
