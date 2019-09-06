package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer){
        return customerRepository.saveAndFlush(customer);
    }

    public Customer getCustomerById(Long id){
        return customerRepository.getOne(id);
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Long id, Customer customer){
        Customer existingCustomer = customerRepository.findById(id).orElse(new Customer());
        BeanUtils.copyProperties(customer, existingCustomer);
        return customerRepository.saveAndFlush(existingCustomer);
    }

    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);      // TODO: adja esetleg vissza a törölt Customert?
    }
}
