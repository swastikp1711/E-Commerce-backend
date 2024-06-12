package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.Entity.SubCategory;
import com.accolite.ecommercebackend.dto.Response.SubCategoryAdminResponse;

import java.util.List;
import java.util.UUID;

public interface AdminSubCategoryService {
    public SubCategory createSubCategory(String categoryName, SubCategory subCategory);
    public List<SubCategoryAdminResponse> getAllSubCategories();
}
