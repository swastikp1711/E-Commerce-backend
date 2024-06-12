package com.accolite.ecommercebackend.Service.Impl;
import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Entity.SubCategory;
import com.accolite.ecommercebackend.Repository.AdminCategoryRepository;
import com.accolite.ecommercebackend.Repository.AdminSubCategoryRepository;
import com.accolite.ecommercebackend.Service.AdminSubCategoryService;
import com.accolite.ecommercebackend.dto.Response.SubCategoryAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminSubCategoryServiceImpl implements AdminSubCategoryService {

    @Autowired
    private AdminSubCategoryRepository adminSubCategoryRepository;
    @Autowired
    private AdminCategoryRepository adminCategoryRepository;

    public SubCategory createSubCategory(String categoryName, SubCategory subCategory) {
        Category category = adminCategoryRepository.findByCategoryNameAndDeletedDateNull(categoryName);
        subCategory.setCategory(category);
        subCategory.setCreatedDate(LocalDateTime.now());
        return adminSubCategoryRepository.save(subCategory);
    }

    public List<SubCategoryAdminResponse> getAllSubCategories() {
        return adminSubCategoryRepository.findAvailableSubcategory()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }
    public List<SubCategoryAdminResponse> getAllSubCategoriesbyCategoryName(String categoryName){
        return adminSubCategoryRepository.findByCategoryCategoryNameAndDeletedDateIsNull(categoryName).stream()
                .map(subCategory -> mapToDto(subCategory)).collect(Collectors.toList());
    }
    public SubCategoryAdminResponse mapToDto(SubCategory subCategory){
        SubCategoryAdminResponse response = new SubCategoryAdminResponse();
        response.setSubCategoryId(subCategory.getSubCategoryId());
        response.setSubCategoryName(subCategory.getSubCategoryName());
        return response;
    }
}