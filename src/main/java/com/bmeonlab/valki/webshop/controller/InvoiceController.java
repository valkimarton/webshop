package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.dto.InvoiceDTO;
import com.bmeonlab.valki.webshop.dto.ProductInCartDTO;
import com.bmeonlab.valki.webshop.model.Invoice;
import com.bmeonlab.valki.webshop.model.ProductInCart;
import com.bmeonlab.valki.webshop.service.InvoiceService;
import com.bmeonlab.valki.webshop.service.ProductInCartService;
import com.bmeonlab.valki.webshop.utils.DTOConverter;
import com.bmeonlab.valki.webshop.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ProductInCartService productInCartService;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private LogUtils logUtils;

    Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @GetMapping("{customerId}")
    public List<InvoiceDTO> getInvoicesByCustomerId(@PathVariable Long customerId, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        List<Invoice> invoices = invoiceService.getInvoicesByCustomerId(customerId);
        return dtoConverter.toInvoiceDTOList(invoices);
    }

    @GetMapping("customer")
    public List<InvoiceDTO> getInvoicesByRequesterCustomer(HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        List<Invoice> invoices = invoiceService.getInvoicesByRequesterCustomer();
        return dtoConverter.toInvoiceDTOList(invoices);
    }

    @GetMapping("{id}/products")
    public List<ProductInCartDTO> getProductInCartsByInvoiceId(@PathVariable Long id, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        List<ProductInCart> productInCarts = productInCartService.getProductInCartsByInvoiceId(id);
        return dtoConverter.toProductInCartDTOList(productInCarts);
    }
}
