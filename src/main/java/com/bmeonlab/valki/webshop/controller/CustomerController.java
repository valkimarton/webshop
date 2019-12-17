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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private LogUtils logUtils;

    Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping
    public List<CustomerDTO> getAllCustomers(HttpServletRequest request){
        logger.warn(logUtils.generateLogMsg(request));

        List<Customer>  customers = customerService.getCustomers();
        return dtoConverter.toCustomerDTOList(customers);
    }

    @GetMapping(value = "{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id, HttpServletRequest request){
        logger.warn(logUtils.generateLogMsg(request));

        Customer customer = customerService.getCustomerById(id);
        return dtoConverter.toCustomerDTO(customer);
    }

    @GetMapping(value = "username/{username}")
    public CustomerDTO getCustomerByUsername(@PathVariable String username, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Customer customer = customerService.getCustomerByUsername(username);
        return dtoConverter.toCustomerDTO(customer);
    }

    @GetMapping("myself")
    public CustomerDTO getRequesterCustomer(HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Customer customer = customerService.getRequesterCustomer();
        return dtoConverter.toCustomerDTO(customer);
    }

    @PostMapping
    public CustomerDTO createCustomer(@Valid @NotNull @RequestBody CustomerRegistrationDTO customerDTO, HttpServletRequest request) throws WebshopException {
        logger.warn(logUtils.generateLogMsg(request));

        Customer customer = dtoConverter.toCustomer(customerDTO);
        Customer createdCustomer = customerService.createCustomer(customer);
        return dtoConverter.toCustomerDTO(createdCustomer);
    }

    @PutMapping(value = "{id}")
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO, HttpServletRequest request){
        logger.warn(logUtils.generateLogMsg(request));

        Customer customer = dtoConverter.toCustomer(customerDTO);
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return dtoConverter.toCustomerDTO(updatedCustomer);
    }

    @DeleteMapping(value = "{id}")
    public void deleteCustomer(@PathVariable Long id, HttpServletRequest request){
        logger.warn(logUtils.generateLogMsg(request));

        customerService.deleteCustomer(id);
    }
}
