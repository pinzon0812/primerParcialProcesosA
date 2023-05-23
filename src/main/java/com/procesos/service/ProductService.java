package com.procesos.service;

import com.procesos.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    private static final String API_URL = "https://fakestoreapi.com/products";

    private final RestTemplate restTemplate;

    public ProductService() {
        this.restTemplate = new RestTemplate();
    }

    public Product[] getProductsFromExternalAPI() {
        return restTemplate.getForObject(API_URL, Product[].class);
    }

    public Product createProduct(Product product) {
        return restTemplate.postForObject(API_URL, product, Product.class);
    }

    public void updateProduct(Product product) {
        restTemplate.put(API_URL + "/" + product.getId(), product);
    }

    public void deleteProduct(Long productId) {
        restTemplate.delete(API_URL + "/" + productId);
    }
}