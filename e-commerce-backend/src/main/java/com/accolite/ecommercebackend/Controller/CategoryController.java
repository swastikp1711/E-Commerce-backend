package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.CategoryService;
import com.accolite.ecommercebackend.dto.Request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.addCategory(categoryRequest);
        return new ResponseEntity<>("Category added successfully", HttpStatus.CREATED);
    }
}

