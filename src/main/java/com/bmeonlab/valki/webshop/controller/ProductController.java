package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.dto.ProductDTO;
import com.bmeonlab.valki.webshop.dto.ReviewDTO;
import com.bmeonlab.valki.webshop.model.Product;
import com.bmeonlab.valki.webshop.model.Review;
import com.bmeonlab.valki.webshop.service.ProductService;
import com.bmeonlab.valki.webshop.service.ReviewService;
import com.bmeonlab.valki.webshop.utils.DTOConverter;
import com.bmeonlab.valki.webshop.utils.LogUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private DTOConverter dtoConverter;

    @Autowired
    private LogUtils logUtils;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public List<ProductDTO> getProducts(HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        List<Product> products = productService.getProducts();
        return dtoConverter.toProductDTOList(products);
    }

    @GetMapping(value = "{id}")
    public ProductDTO getProductById(@PathVariable Long id, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Product product = productService.getProductById(id);
        return dtoConverter.toProductDTO(product);
    }

    @GetMapping(value = "{id}/reviews")
    public List<ReviewDTO> getReviewsByProductId(@PathVariable Long id, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        List<Review> reviews = reviewService.getReviewsByProductId(id);
        return dtoConverter.toReviewDTOList(reviews);
    }

    @PostMapping
    public ProductDTO createProduct(@Valid @NotNull @RequestBody ProductDTO productDTO, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Product product = dtoConverter.toProduct(productDTO);
        Product createdProduct = productService.createProduct(product);
        return dtoConverter.toProductDTO(createdProduct);
    }

    @PutMapping(value = "{id}")
    public ProductDTO updateProduct(@PathVariable Long id, @Valid @NotNull @RequestBody ProductDTO productDTO, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        Product product = dtoConverter.toProduct(productDTO);
        Product updatedProduct = productService.updateProduct(id, product);
        return dtoConverter.toProductDTO(updatedProduct);
    }

    @DeleteMapping(value = "{id}")
    public void deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        logger.warn(logUtils.generateLogMsg(request));

        productService.deleteProduct(id);
    }
}
