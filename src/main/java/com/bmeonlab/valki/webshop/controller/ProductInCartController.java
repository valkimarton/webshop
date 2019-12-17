package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.dto.ProductInCartDTO;
import com.bmeonlab.valki.webshop.model.ProductInCart;
import com.bmeonlab.valki.webshop.service.CartService;
import com.bmeonlab.valki.webshop.service.CustomerService;
import com.bmeonlab.valki.webshop.service.ProductInCartService;
import com.bmeonlab.valki.webshop.service.ProductService;
import com.bmeonlab.valki.webshop.utils.DTOConverter;
import com.bmeonlab.valki.webshop.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/product_in_cart")
public class ProductInCartController {

    @Autowired
    private ProductInCartService productInCartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private LogUtils logUtils;

    Logger logger = LoggerFactory.getLogger(ProductInCartController.class);

    @PostMapping
    public ProductInCartDTO addProductInCartToCart(@RequestBody ProductInCartDTO productInCartDTO, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        ProductInCart productInCart = dtoConverter.toProductInCart(productInCartDTO);
        ProductInCart created = productInCartService.createProductInCart(productInCart);
        return dtoConverter.toProductInCartDTO(created);
    }

    @GetMapping("{userId}")
    public List<ProductInCartDTO> getProductInCartsByUserId(@PathVariable Long userId, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        List<ProductInCart> productInCarts = productInCartService.getProductInCartsByUserId(userId);
        return dtoConverter.toProductInCartDTOList(productInCarts);
    }

    @DeleteMapping("{id}")
    public void deleteProductInCart(@PathVariable Long id, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        productInCartService.deleteProductInCart(id);
    }

    @PostMapping("buy")
    public List<ProductInCartDTO> buyProductInCarts(HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        List<ProductInCart> productInCarts = productInCartService.buyProductInCarts();
        return dtoConverter.toProductInCartDTOList(productInCarts);
    }

}
