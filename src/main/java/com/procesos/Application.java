package com.procesos;

import com.procesos.models.Product;
import com.procesos.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {
    private final ProductService productService;

    public Application(ProductService productService) {
        this.productService = productService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void loadDataFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = "https://646d85949c677e23218a1155.mockapi.io/api/v1/products";
        Product[] products = restTemplate.getForObject(apiUrl, Product[].class);

        for (Product product : products) {
            productService.saveProduct(product);
        }
    }
}