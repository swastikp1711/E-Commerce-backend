package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Product;
import com.accolite.ecommercebackend.Repository.AdminCategoryRepository;
import com.accolite.ecommercebackend.Repository.AdminProductRepository;
import com.accolite.ecommercebackend.Repository.AdminSubCategoryRepository;
import com.accolite.ecommercebackend.Service.AdminProductService;
import com.accolite.ecommercebackend.dto.Request.ProductRequestadmin;
import com.accolite.ecommercebackend.dto.Request.UpdateProductRequest;
import com.accolite.ecommercebackend.dto.Response.ProductAdminResponse;
import com.accolite.ecommercebackend.dto.Response.ProductResponseadmin;
import com.accolite.ecommercebackend.dto.Response.UpdateProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminProductServiceImpl implements AdminProductService {
    @Autowired
    private AdminProductRepository adminProductRepository;
    @Autowired
    private AdminCategoryRepository adminCategoryRepository;
    @Autowired
    private AdminSubCategoryRepository adminSubCategoryRepository;

    public ProductResponseadmin createProduct(ProductRequestadmin productRequestadmin) {
        Product product = new Product();
        mapToEntity(productRequestadmin, product);
        product.setCreatedDate(LocalDateTime.now());
        adminProductRepository.save(product);
        return mapToDto(product);
    }

    public UpdateProductRequest updateProduct(UUID productId, UpdateProductRequest updateProductRequest){
        Optional<Product> optionalProduct = adminProductRepository.findByProductId(productId);
        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            mapToLimitedDto(updateProductRequest,product);
            product.setUpdatedDate(LocalDateTime.now());
            adminProductRepository.save(product);
            return updateProductRequest;
        }else {
            throw new RuntimeException("Product not found");
        }}


    private void mapToLimitedDto(UpdateProductRequest updateProductRequest,Product product) {
        product.setPrice(updateProductRequest.getPrice());
        product.setQuantityAvailable(updateProductRequest.getQuantityAvailable());
        product.setDiscountPercent(updateProductRequest.getDiscountPercent());
        product.setDeliveryCharges(updateProductRequest.getDeliveryCharges());
    }

    public List<ProductResponseadmin> fetchAllProducts(){
        return adminProductRepository.findAvailableProducts()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }


    public void mapToEntity(ProductRequestadmin productRequestadmin, Product product) {
        product.setImageUrl(productRequestadmin.getImageUrl());
        product.setTitle(productRequestadmin.getTitle());
        product.setSubtitle(productRequestadmin.getSubtitle());
        product.setDescription(productRequestadmin.getDescription());
        product.setProductHighlights(productRequestadmin.getProductHighlights());
        product.setBrand(productRequestadmin.getBrand());
        product.setQuantityAvailable(productRequestadmin.getQuantityAvailable());
        product.setPrice(productRequestadmin.getPrice());
        product.setDiscountPercent(productRequestadmin.getDiscountPercent());
        product.setDeliveryCharges(productRequestadmin.getDeliveryCharges());
        product.setCategory(adminCategoryRepository.findByCategoryNameAndDeletedDateNull(productRequestadmin.getCategory())); // Assuming constructor
        product.setSubCategory(adminSubCategoryRepository.findBySubCategoryNameAndDeletedDateNull(productRequestadmin.getSubCategory())); // Assuming constructor

    }

    public ProductResponseadmin mapToDto(Product product) {
        ProductResponseadmin response = new ProductResponseadmin();
        response.setProductId(product.getProductId());
        response.setImageUrl(product.getImageUrl());
        response.setTitle(product.getTitle());
        response.setSubtitle(product.getSubtitle());
        response.setDescription(product.getDescription());
        response.setProductHighlights(product.getProductHighlights());
        response.setBrand(product.getBrand());
        response.setQuantityAvailable(product.getQuantityAvailable());
        response.setPrice(product.getPrice());
        response.setDiscountPercent(product.getDiscountPercent());
        response.setDeliveryCharges(product.getDeliveryCharges());
        response.setCreatedDate(product.getCreatedDate());
        response.setUpdatedDate(product.getUpdatedDate());
        response.setCategoryName(product.getCategory().getCategoryName());
        response.setSubCategoryName(product.getSubCategory().getSubCategoryName());
        return response;
    }

    public ProductResponseadmin deleteProduct(UUID productId) {
        Product product = adminProductRepository.findByProductIdAndDeletedDateIsNullAndQuantityAvailableGreaterThan(productId, 0)
                .orElseThrow(() -> new RuntimeException("Product not found or is unavailable"));
        product.setDeletedDate(LocalDateTime.now());
        adminProductRepository.save(product);
        return mapToDto(product);
    }

    public ProductResponseadmin fetchProductById(UUID productId) {
        Product product = adminProductRepository.findByProductIdAndDeletedDateIsNullAndQuantityAvailableGreaterThan(productId, 0)
                .orElseThrow(() -> new RuntimeException("Product not found or is unavailable"));
        return mapToDto(product);
    }
}
