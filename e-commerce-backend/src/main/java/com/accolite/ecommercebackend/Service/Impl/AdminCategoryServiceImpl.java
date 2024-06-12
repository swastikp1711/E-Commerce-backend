package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Repository.AdminCategoryRepository;
import com.accolite.ecommercebackend.Service.AdminCategoryService;
import com.accolite.ecommercebackend.dto.Response.CategoryAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
    @Autowired
    private AdminCategoryRepository adminCategoryRepository;

    public Category createCategory(Category category) {
        return adminCategoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return adminCategoryRepository.findAvailableCategory();
    }

}
