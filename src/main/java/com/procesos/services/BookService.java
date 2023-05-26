package com.procesos.services;

import com.procesos.models.Book;

import java.util.List;

public interface BookService {
    Book getProduct(Long id);
    List<Book> allProduct();
    Boolean createProduct();
    Boolean updateProduct(Long id, Book book);
}