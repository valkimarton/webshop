package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Customer;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;

public class CustomerServiceTest {

    private CustomerService customerService = new CustomerService();

    @Test
    public void getCustomerByUsernameTest(){
        String username = "GombocA";

        Customer customer = customerService.getCustomerByUsername(username);
        Assert.assertEquals(username, customer.getUsername());
    }
}
