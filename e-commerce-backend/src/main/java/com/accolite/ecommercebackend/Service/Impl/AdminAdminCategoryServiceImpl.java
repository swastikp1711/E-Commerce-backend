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
public class AdminAdminCategoryServiceImpl implements AdminCategoryService {
    @Autowired
    private AdminCategoryRepository adminCategoryRepository;

    public Category createCategory(Category category) {
        return adminCategoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return adminCategoryRepository.findAvailableCategory();
    }

    public Category getCategoryById(UUID categoryId) {
        return adminCategoryRepository.findAvailableCategoryById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public Category updateCategory(UUID categoryId, Category categoryDetails) {
        Category category = getCategoryById(categoryId);
        category.setCategoryName(categoryDetails.getCategoryName());
        category.setUpdatedDate(LocalDateTime.now());
        return adminCategoryRepository.save(category);
    }

    public CategoryAdminResponse mapToDto(Category category){
        CategoryAdminResponse categoryAdminResponse = new CategoryAdminResponse();
        categoryAdminResponse.setCategoryId(categoryAdminResponse.getCategoryId());
        categoryAdminResponse.setCategoryName(categoryAdminResponse.getCategoryName());
        return categoryAdminResponse;
    }

    public CategoryAdminResponse deleteCategory(UUID categoryId){
        Category category = adminCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.setDeletedDate(LocalDateTime.now());
        adminCategoryRepository.save(category);
        return mapToDto(category);
    }
}
