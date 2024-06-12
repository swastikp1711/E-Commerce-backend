package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.dto.Response.CategoryAdminResponse;

import java.util.List;
import java.util.UUID;

public interface AdminCategoryService {
    public Category createCategory(Category category);
    public List<Category> getAllCategories();
}
