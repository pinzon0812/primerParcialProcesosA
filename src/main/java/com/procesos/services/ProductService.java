package com.procesos.services;

import com.procesos.models.Product;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);
    List<Product> allProduct();
    Boolean createProduct();
    Boolean updateProduct(Long id, Product product);
}