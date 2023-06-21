package com.procesos.controllers;


import com.procesos.models.Product;
import com.procesos.services.ProductServiceImp;
import com.procesos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductServiceImp serviceImp;
    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "")
    public ResponseEntity findAllProduct(@RequestHeader(value = "Authorization") String token){
        if(!AuthToken(token)){return new ResponseEntity<>("Error Token",HttpStatus.UNAUTHORIZED);}
        Map response = new HashMap();
        try {
            return new ResponseEntity(serviceImp.allProduct(), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            response.put("message","No se encontraron los productos!");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findProductById(@PathVariable Long id,@RequestHeader(value = "Authorization") String token){
        if(!AuthToken(token)){return new ResponseEntity<>("Error Token",HttpStatus.UNAUTHORIZED);}
        Map response = new HashMap();
        try {
            return new ResponseEntity(serviceImp.getProduct(id), HttpStatus.OK);
        }catch(Exception e){
            response.put("status","404");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity saveProduct(@RequestHeader(value = "Authorization") String token){
        if(!AuthToken(token)){return new ResponseEntity<>("Error Token",HttpStatus.UNAUTHORIZED);}
        Map response = new HashMap();
        Boolean userResp = serviceImp.createProduct();

        if(userResp == true){
            response.put("status", "201");
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        response.put("status","400");
        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    private Boolean AuthToken(String token){
        try{
            if(jwtUtil.getKey(token) != null){
                return true;
            }
            return  false;
        }catch (Exception e){
            return  false;
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product, @RequestHeader(value = "Authorization") String token){
        if(!AuthToken(token)){return new ResponseEntity<>("Error Token",HttpStatus.UNAUTHORIZED);}
        Boolean userResp = serviceImp.updateProduct(id, product);
        Map map = new HashMap();

        if(userResp == true){
            map.put("status", "200");
            return new ResponseEntity(map, HttpStatus.OK);
        }
        map.put("status","400");
        return new ResponseEntity(map,HttpStatus.BAD_REQUEST);
    }

}
