package com.procesos.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private String Price;
    @Column(name = "description")
    private String Description;
    @Column(name = "category")
    private String Category;
    @Column(name = "image")
    private String Image;

    public Product() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
