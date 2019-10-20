package com.bmeonlab.valki.webshop.controller;

import com.bmeonlab.valki.webshop.model.Product;
import com.bmeonlab.valki.webshop.model.Review;
import com.bmeonlab.valki.webshop.service.ProductService;
import com.bmeonlab.valki.webshop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<Product> getProducts() {
        List<Product> products = productService.getProducts();
        products.forEach(product -> System.out.println(product));
        return products;

        // return productService.getProducts();     TODO: put this one back
    }

    @GetMapping(value = "{id}")
    public Product getProductById(@PathVariable  Long id) {
        return productService.getProductById(id);
    }

    @GetMapping(value = "{id}/reviews")
    public List<Review> getReviewsByProductId(@PathVariable Long id) {
        List<Review> reviews = reviewService.getReviewsByProductId(id);
        return reviews;
    }

    @PostMapping
    public Product createProduct(@Valid @NotNull @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping(value = "{id}")
    public Product updateProduct(@PathVariable Long id, @Valid @NotNull @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(value = "{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
