package com.procesos.services;


import com.procesos.models.Book;
import com.procesos.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class BookServiceImp implements BookService {

    private final RestTemplate restTemplate;
    private BookRepository bookRepository;

    public BookServiceImp(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getProduct(Long id){
        return bookRepository.findById(id).get();
    }

    @Override
    public Boolean updateProduct(Long id, Book book) {
        try {
            Book productsDatos = bookRepository.findById(id).get();
            productsDatos.setTitle(book.getTitle());
            productsDatos.setDescription(book.getDescription());
            productsDatos.setPrice(book.getPrice());
            productsDatos.setStock(book.getStock());
            productsDatos.setCategory(book.getCategory());
            Book productosUp = bookRepository.save(productsDatos);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Book> allProduct() {
        return bookRepository.findAll();
    }

    @Override
    public Boolean createProduct() {

        try {
            String url="https://646d85949c677e23218a1155.mockapi.io/api/v1/products";
            Book[] response= restTemplate.getForObject(url, Book[].class);
            bookRepository.saveAll(Arrays.asList(response));
            return true;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

}