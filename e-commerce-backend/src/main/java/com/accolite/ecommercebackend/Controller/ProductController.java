package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.ProductService;
import com.accolite.ecommercebackend.dto.Request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;



    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) {
        productService.addProduct(productRequest);
        return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
    }
}
