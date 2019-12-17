package com.bmeonlab.valki.webshop.controller;


import com.bmeonlab.valki.webshop.dto.CustomerDTO;
import com.bmeonlab.valki.webshop.dto.CustomerRegistrationDTO;
import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.service.CustomerService;
import com.bmeonlab.valki.webshop.utils.DTOConverter;
import com.bmeonlab.valki.webshop.utils.LogUtils;
import com.bmeonlab.valki.webshop.utils.exceptions.WebshopException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private LogUtils logUtils;

    Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @PostMapping
    public CustomerDTO registerUser(@Valid @NotNull @RequestBody CustomerRegistrationDTO customerRegDTO, HttpServletRequest request) throws WebshopException {
        logger.warn(logUtils.generateLogMsg(request));

        Customer customer = dtoConverter.toCustomer(customerRegDTO);
        Customer createdCustomer = customerService.createCustomer(customer);
        return dtoConverter.toCustomerDTO(createdCustomer);
    }
}
