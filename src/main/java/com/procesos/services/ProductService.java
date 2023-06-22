package com.procesos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.procesos.models.Products;

import java.util.List;

public interface ProductService {
    Products getProduct(Long id);
    List<Products> allProduct();
    Boolean createProduct(Long id, Long id_user) throws JsonProcessingException;
    Boolean deleteProduct(Long id);
    Boolean updateProduct(Long id, Products products);
}