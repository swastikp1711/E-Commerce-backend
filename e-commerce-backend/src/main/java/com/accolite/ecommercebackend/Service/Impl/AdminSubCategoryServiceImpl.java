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
        Category category = adminCategoryRepository.findCategoryByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        subCategory.setCategory(category);
        subCategory.setCreatedDate(LocalDateTime.now());
        return adminSubCategoryRepository.save(subCategory);
    }

    public List<SubCategoryAdminResponse> getAllSubCategories() {
        return adminSubCategoryRepository.findAvailableSubcategory()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }
    public List<SubCategoryAdminResponse> getAllSubCategoriesbyCategoryName(String categoryName){
        return adminSubCategoryRepository.findbyCategoryByName(categoryName).stream()
                .map(subCategory -> mapToDto(subCategory)).collect(Collectors.toList());
    }
//    @Override
//    public List<SubCategoryAdminResponse> getAllSubCategoriesByCategoryName(String categoryName) {
//        return subCategoryRepository.findbyCategoryByName(categoryName).stream()
//                .map(this::mapToSubCategoryAdminResponse)
//                .collect(Collectors.toList());
//    }
//
//    private SubCategoryAdminResponse mapToSubCategoryAdminResponse(SubCategory subCategory) {
//        SubCategoryAdminResponse response = new SubCategoryAdminResponse();
//        response.setSubCategoryId(subCategory.getSubCategoryId());
//        // Set other properties as needed
//        return response;
//    }

    public SubCategory getSubCategoryById(UUID subCategoryId)  {
        return adminSubCategoryRepository.findAvailableSubcategoryById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
    }
    public SubCategoryAdminResponse mapToDto(SubCategory subCategory){
        SubCategoryAdminResponse response = new SubCategoryAdminResponse();
        response.setSubCategoryId(subCategory.getSubCategoryId());
        response.setSubCategoryName(subCategory.getSubCategoryName());
//        response.setCategoryName(subCategory.getCategory().getCategoryName());
        return response;
    }



    public SubCategory updateSubCategory(UUID subCategoryId, SubCategory subCategoryDetails) {
        SubCategory subCategory = adminSubCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        subCategory.setSubCategoryName(subCategoryDetails.getSubCategoryName());
        subCategory.setCategory(subCategoryDetails.getCategory());
        subCategory.setUpdatedDate(LocalDateTime.now());
        return adminSubCategoryRepository.save(subCategory);
    }

    //    public void deleteSubCategory(UUID subCategoryId) {
//        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
//                        .orElseThrow(() -> new RuntimeException("Sub category not found"));
//        subCategoryRepository.delete(subCategory);
//    }
    public SubCategoryAdminResponse deleteSubCategory(UUID subCategoryId) {
        SubCategory subCategory = adminSubCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("Sub category not found or is unavailable"));
        subCategory.setDeletedDate(LocalDateTime.now());
        adminSubCategoryRepository.save(subCategory);
        return mapToDto(subCategory);
    }


}