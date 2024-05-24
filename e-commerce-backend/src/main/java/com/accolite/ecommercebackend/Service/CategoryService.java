package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.Request.CategoryRequest;
import com.accolite.ecommercebackend.dto.Response.CategoryResponse;

public interface CategoryService {
    void addCategory(CategoryRequest categoryRequest);

    CategoryResponse getAllActiveCategoriesAndSubCategories();
}
