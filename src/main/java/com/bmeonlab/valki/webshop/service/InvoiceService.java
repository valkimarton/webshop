package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Invoice;
import com.bmeonlab.valki.webshop.repository.InvoiceRepository;
import com.bmeonlab.valki.webshop.repository.InvoiceRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) { return invoiceRepository.saveAndFlush(invoice); }

    public Invoice getInvoiceById(Long id) { return invoiceRepository.getOne(id); }

    public List<Invoice> getAllInvoices() {return  invoiceRepository.findAll(); }

    public List<Invoice> getInvoicesByCustomerId(Long customerId) { return invoiceRepository.findByCustomerId(customerId); }

    public Invoice updateInvoice(Long id, Invoice invoice) {
        Invoice existingInvoice = invoiceRepository.findById(id).orElse(new Invoice());
        NullAwareBeanUtils.copyNonNullProperties(invoice, existingInvoice);
        return invoiceRepository.saveAndFlush(existingInvoice);
    }

    public void deleteInvoice(Long id) { invoiceRepository.deleteById(id); }
}
