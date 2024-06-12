package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Service.Impl.AdminCategoryServiceImpl;
import com.accolite.ecommercebackend.dto.Request.CategoryAdminRequest;
import com.accolite.ecommercebackend.dto.Response.CategoryAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    @Autowired
    private AdminCategoryServiceImpl adminCategoryServiceImpl;

    @PostMapping("/addCategory")
    public CategoryAdminResponse createCategory(@RequestBody CategoryAdminRequest categoryAdminRequest) {
        Category category = new Category();
        category.setCategoryName(categoryAdminRequest.getCategoryName());
        category.setCreatedDate(LocalDateTime.now());
        Category newCategory = adminCategoryServiceImpl.createCategory(category);
        return new CategoryAdminResponse(newCategory.getCategoryId(),newCategory.getCategoryName());
    }

    @GetMapping
    public List<CategoryAdminResponse> getAllCategories() {
        return adminCategoryServiceImpl.getAllCategories().stream()
                .map(category -> new CategoryAdminResponse(category.getCategoryId(),category.getCategoryName()))
                .collect(Collectors.toList());
    }
}
