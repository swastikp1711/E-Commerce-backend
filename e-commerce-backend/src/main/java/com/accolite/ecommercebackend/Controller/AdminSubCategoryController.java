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
        SubCategory newSubCategory = adminSubCategoryServiceImpl.createSubCategory(subCategoryAdminRequest.getCategoryId(),subCategory);
//        return new SubCategoryAdminResponse(newSubCategory.getSubCategoryId(),newSubCategory.getSubCategoryName(),newSubCategory.getCategory().getCategoryId());
        return adminSubCategoryServiceImpl.mapToDto(newSubCategory);
    }

    @GetMapping
    public List<SubCategoryAdminResponse> getAllSubCategories() {
        return adminSubCategoryServiceImpl.getAllSubCategories();
    }

    @GetMapping("/{subCategoryId}")
    public SubCategory getSubCategoryById(@PathVariable UUID subCategoryId) {
//        SubCategoryAdminResponse subCategory = subCategoryServiceImpl.getSubCategoryById(subCategoryId);
//        return new SubCategoryAdminResponse(subCategory.getSubCategoryId(),subCategory.getSubCategoryName(),subCategory.getCategory().getCategoryId());
        return  adminSubCategoryServiceImpl.getSubCategoryById(subCategoryId);
    }

//    @GetMapping("/category/{categoryId}")
//    public ResponseEntity<List<SubCategoryAdminResponse>> getAllSubCategoriesByCategory(@PathVariable UUID categoryId){
////        return subCategoryServiceImpl.getSubcategoryByCategoryId(categoryId);
//        List<SubCategoryAdminResponse> subCategories = subCategoryServiceImpl.getSubcategoryByCategoryId(categoryId);
//        return ResponseEntity.ok(subCategories);
//    }

    @GetMapping("category/{categoryName}")
    public List<SubCategoryAdminResponse> getAllSubCategoriesByCategory(@PathVariable String categoryName){
        return adminSubCategoryServiceImpl.getAllSubCategoriesbyCategoryName(categoryName);
    }

//    @PutMapping("/{subCategoryId}")
//    public SubCategoryAdminResponse updateSubCategory(@PathVariable UUID subCategoryId, @RequestBody SubCategoryAdminRequest subCategoryAdminRequest) {
//        SubCategory subCategory = new SubCategory();
//        subCategory.setSubCategoryName(subCategoryAdminRequest.getSubCategoryName());
////        subCategory.setCategory(categoryRepository.findById(subCategoryAdminRequest.getCategoryId()));
//        SubCategory newSubCategory = subCategoryServiceImpl.updateSubCategory(subCategoryId, subCategory);
//        return new SubCategoryAdminResponse(newSubCategory.getSubCategoryId(), newSubCategory.getSubCategoryName(), newSubCategory.getCategory().getCategoryName());
//    }

    @DeleteMapping("/{subCategoryId}")
    public ResponseEntity<SubCategoryAdminResponse> deleteSubCategory(@PathVariable UUID subCategoryId) {
        return ResponseEntity.ok(adminSubCategoryServiceImpl.deleteSubCategory(subCategoryId));
    }
}