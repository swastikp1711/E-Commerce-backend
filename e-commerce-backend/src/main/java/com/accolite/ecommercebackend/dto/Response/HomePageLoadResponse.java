package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class HomePageLoadResponse {

    private List<SubCategoryInfo> categories;

    @Data
    @AllArgsConstructor
    public static class SubCategoryInfo {
        private UUID subCategoryId;
        private String subCategoryName;
        private List<ProductInfo> products;
    }

    @Data
    @AllArgsConstructor
    public static class ProductInfo {
        private UUID productId;
        private String imageUrl;
        private String title;
        private String subtitle;
        private String brand;
        private Double price;
        private Double discountPercent;
        private Integer productQuantity;
    }
}

//
//onHomePageLoad
//1.categories, subCategories
//2. min 10 products from 5-7 subcategoies
//3. cartItems count