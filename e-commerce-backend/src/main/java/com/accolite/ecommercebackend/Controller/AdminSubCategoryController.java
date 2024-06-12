package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Entity.SubCategory;
import com.accolite.ecommercebackend.Repository.AdminCategoryRepository;
import com.accolite.ecommercebackend.Service.Impl.AdminSubCategoryServiceImpl;
import com.accolite.ecommercebackend.dto.Request.SubCategoryAdminRequest;
import com.accolite.ecommercebackend.dto.Response.SubCategoryAdminResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/subcategories")
public class AdminSubCategoryController {

    @Autowired
    private AdminSubCategoryServiceImpl adminSubCategoryServiceImpl;
    private AdminCategoryRepository adminCategoryRepository;

    @PostMapping
    public SubCategoryAdminResponse createSubCategory(@RequestBody SubCategoryAdminRequest subCategoryAdminRequest) {
        SubCategory subCategory = new SubCategory();
        subCategory.setSubCategoryName(subCategoryAdminRequest.getSubCategoryName());
        SubCategory newSubCategory = adminSubCategoryServiceImpl.createSubCategory(subCategoryAdminRequest.getCategoryName(),subCategory);
        return adminSubCategoryServiceImpl.mapToDto(newSubCategory);
    }

    @GetMapping
    public List<SubCategoryAdminResponse> getAllSubCategories() {
        return adminSubCategoryServiceImpl.getAllSubCategories();
    }


    @GetMapping("category/{categoryName}")
    public List<SubCategoryAdminResponse> getAllSubCategoriesByCategory(@PathVariable String categoryName){
        return adminSubCategoryServiceImpl.getAllSubCategoriesbyCategoryName(categoryName);
    }
}