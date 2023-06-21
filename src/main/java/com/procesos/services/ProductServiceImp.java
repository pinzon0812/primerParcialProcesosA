package com.procesos.services;


import com.procesos.models.Product;
import com.procesos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class ProductServiceImp implements ProductService {

    private final RestTemplate restTemplate;
    private ProductRepository productRepository;

    public ProductServiceImp(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    @Override
    public Product getProduct(Long id){
        return productRepository.findById(id).get();
    }

    @Override
    public Boolean updateProduct(Long id, Product product) {
        try {
            Product productsDatos = productRepository.findById(id).get();
            productsDatos.setTitle(product.getTitle());
            productsDatos.setDescription(product.getDescription());
            productsDatos.setPrice(product.getPrice());
            productsDatos.setStock(product.getStock());
            productsDatos.setCategory(product.getCategory());
            Product productosUp = productRepository.save(productsDatos);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Product> allProduct() {
        return productRepository.findAll();
    }

    @Override
    public Boolean createProduct() {

        try {
            String url="https://646d85949c677e23218a1155.mockapi.io/api/v1/products";
            Product[] response= restTemplate.getForObject(url, Product[].class);
            productRepository.saveAll(Arrays.asList(response));
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

}