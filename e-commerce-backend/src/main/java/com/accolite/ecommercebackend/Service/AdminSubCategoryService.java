package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.Entity.SubCategory;
import com.accolite.ecommercebackend.dto.Response.SubCategoryAdminResponse;

import java.util.List;
import java.util.UUID;

public interface AdminSubCategoryService {
    public SubCategory createSubCategory(UUID categoryId, SubCategory subCategory);
    public List<SubCategoryAdminResponse> getAllSubCategories();
    public SubCategory getSubCategoryById(UUID subCategoryId);
    public SubCategoryAdminResponse mapToDto(SubCategory subCategory);
    public SubCategory updateSubCategory(UUID subCategoryId, SubCategory subCategoryDetails);
    public SubCategoryAdminResponse deleteSubCategory(UUID subCategoryId);
//    public List<SubCategoryAdminResponse> getSubcategoryByCategoryId(UUID categoryId);
}
