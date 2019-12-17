package com.bmeonlab.valki.webshop.service;

import com.bmeonlab.valki.webshop.model.Product;
import com.bmeonlab.valki.webshop.repository.ProductRepository;
import com.bmeonlab.valki.webshop.utils.NullAwareBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product createProduct(Product product) { return productRepository.saveAndFlush(product); }

    public Product getProductById(Long id) { return productRepository.getOne(id); }

    public List<Product> getProducts() { return productRepository.findAll(); }

    @Transactional
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElse(new Product());
        NullAwareBeanUtils.copyNonNullProperties(product, existingProduct);
        return productRepository.saveAndFlush(existingProduct);
    }

    @Transactional
    public void deleteProduct(Long id) { productRepository.deleteById(id); }
}
