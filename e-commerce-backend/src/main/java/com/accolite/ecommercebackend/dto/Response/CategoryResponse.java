package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private List<Category> categories;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category {
        private UUID categoryId;
        private String categoryName;
        private List<SubCategory> subCategories;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SubCategory {
            private UUID subCategoryId;
            private String subCategoryName;
        }
    }
}
