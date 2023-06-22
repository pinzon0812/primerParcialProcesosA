package com.procesos.services;


import com.procesos.models.Products;
import com.procesos.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class ProductServiceImp implements ProductService {

    private final RestTemplate restTemplate;
    private ProductRepository productRepository;
    private UserService userService;

    public ProductServiceImp(RestTemplate restTemplate, ProductRepository productRepository) {
        this.restTemplate = restTemplate;
        this.productRepository = productRepository;
    }

    @Override
    public Products getProduct(Long id){
        return productRepository.findById(id).get();
    }

    @Override
    public Boolean updateProduct(Long id, Products products) {
        try {
            Products productsDatos = productRepository.findById(id).get();
            productsDatos.setTitle(products.getTitle());
            productsDatos.setDescription(products.getDescription());
            productsDatos.setPrice(products.getPrice());
            productsDatos.setStock(products.getStock());
            productsDatos.setCategory(products.getCategory());
            Products productosUp = productRepository.save(productsDatos);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Products> allProduct() {
        return productRepository.findAll();
    }

    @Override
    public Boolean createProduct(Long id, Long id_user) {

        try {
            String url="https://646d85949c677e23218a1155.mockapi.io/api/v1/products";
            Products[] response= restTemplate.getForObject(url, Products[].class);
            productRepository.saveAll(Arrays.asList(response));
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Boolean deleteProduct(Long id){
        try {
            Products product = productRepository.findById(id).get();
            productRepository.delete(product);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}