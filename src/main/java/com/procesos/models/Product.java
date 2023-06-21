package com.procesos.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private double Price;
    @Column(name = "description")
    private String Description;
    @Column(name = "category")
    private String Category;
    @Column(name = "stock")
    private String Stock;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="USER_ID")
    private User user;

}
