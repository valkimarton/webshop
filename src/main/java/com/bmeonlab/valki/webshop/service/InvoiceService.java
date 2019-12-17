package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Customer;
import com.bmeonlab.valki.webshop.model.Invoice;
import com.bmeonlab.valki.webshop.repository.InvoiceRepository;
import com.bmeonlab.valki.webshop.repository.InvoiceRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import com.bmeonlab.valki.webshop.utils.exceptions.UnauthenticatedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CustomerService customerService;

    @Transactional
    public Invoice createInvoice(Invoice invoice) { return invoiceRepository.saveAndFlush(invoice); }

    public Invoice getInvoiceById(Long id) {
        if (id == null)
            return null;
        return invoiceRepository.getOne(id);
    }

    public List<Invoice> getAllInvoices() {return  invoiceRepository.findAll(); }

    public List<Invoice> getInvoicesByCustomerId(Long customerId) { return invoiceRepository.findByCustomerId(customerId); }

    public List<Invoice> getInvoicesByRequesterCustomer() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(username == null || username.equals("anonymousUser"))
            return new ArrayList<>();

        Customer customer = customerService.getCustomerByUsername(username);

        return invoiceRepository.findByCustomerId(customer.getId());
    }

    @Transactional
    public Invoice updateInvoice(Long id, Invoice invoice) {
        Invoice existingInvoice = invoiceRepository.findById(id).orElse(new Invoice());
        NullAwareBeanUtils.copyNonNullProperties(invoice, existingInvoice);
        return invoiceRepository.saveAndFlush(existingInvoice);
    }

    @Transactional
    public void deleteInvoice(Long id) { invoiceRepository.deleteById(id); }
}
