package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Entity.Product;
import com.accolite.ecommercebackend.Entity.SubCategory;
import com.accolite.ecommercebackend.Repository.CategoryRepository;
import com.accolite.ecommercebackend.Repository.ProductRepository;
import com.accolite.ecommercebackend.Repository.SubCategoryRepository;
import com.accolite.ecommercebackend.Service.ProductService;
import com.accolite.ecommercebackend.dto.Request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public void addProduct(ProductRequest productRequest) {

        LocalDateTime createdDate = LocalDateTime.now();

        Product product = new Product();
        product.setImageUrl(productRequest.getImageUrl());
        product.setTitle(productRequest.getTitle());
        product.setSubtitle(productRequest.getSubtitle());
        product.setDescription(productRequest.getDescription());
        product.setProductHighlights(productRequest.getProductHighlights());
        product.setBrand(productRequest.getBrand());
        product.setQuantityAvailable(productRequest.getQuantityAvailable());
        product.setPrice(productRequest.getPrice());
        product.setDiscountPercent(productRequest.getDiscountPercent());
        product.setDeliveryCharges(productRequest.getDeliveryCharges());
        product.setCreatedDate(createdDate);

        Category category = categoryRepository.findById(productRequest.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid category ID"));
        SubCategory subCategory = subCategoryRepository.findById(productRequest.getSubCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid sub-category ID"));

        product.setCategory(category);
        product.setSubCategory(subCategory);

        productRepository.save(product);
    }
}
