package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Repository.CategoryRepository;
import com.accolite.ecommercebackend.Service.CategoryService;
import com.accolite.ecommercebackend.dto.Request.CategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void addCategory(CategoryRequest categoryRequest) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        System.out.println("email -> "+email);
        Category category = new Category();
        category.setCategoryName(categoryRequest.getCategoryName());
        category.setCreatedDate(LocalDateTime.now());

        categoryRepository.save(category);
    }
}
