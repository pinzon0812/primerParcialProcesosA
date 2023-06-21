package com.procesos.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;
    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;
    @Column(length = 100)
    private String address;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 64, nullable = false)
    private String password;
    @Column(length = 30)
    private Date birthday;

    public void addProducts (Product elproduct){
        if (products == null) products = new ArrayList<>();
        products.add(elproduct);
        elproduct.setUser(this);
    }

    @OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Product> products;
}





