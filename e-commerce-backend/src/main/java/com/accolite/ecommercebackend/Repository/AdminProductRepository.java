package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Entity.Product;
import com.accolite.ecommercebackend.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminProductRepository extends JpaRepository<Product, UUID> {
    //    public Product findByProductId(UUID productId);
    @Query("Select c from Category c where c.categoryName=:categoryName")
    Category findbyCategorybyName(String categoryName);

    @Query("Select s from SubCategory s where s.subCategoryName=:subcategoryName")
    SubCategory findbySubCategorybyName(String subcategoryName);
    @Query("SELECT p FROM Product p WHERE p.deletedDate IS NULL AND p.quantityAvailable > 0")
    List<Product> findAvailableProducts();

    @Query("SELECT p FROM Product p WHERE p.productId = :productId AND p.deletedDate IS NULL AND p.quantityAvailable > 0")
    Optional<Product> findAvailableProductById(UUID productId);

    Optional<Product> findByProductIdAndDeletedDateIsNullAndQuantityAvailableGreaterThan(UUID productId, int quantity);
    List<Product> findProductByDeletedDateIsNullAndQuantityAvailableGreaterThan(int quantity);
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId AND p.deletedDate IS NULL AND p.quantityAvailable > 0")
    List<Product> findProductsByCategory(UUID categoryId);

    @Query("SELECT p FROM Product p WHERE p.subCategory.subCategoryId = :subCategoryId AND p.deletedDate IS NULL AND p.quantityAvailable > 0")
    List<Product> findProductsBySubCategory(UUID subCategoryId);

    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId AND p.subCategory.subCategoryId = :subCategoryId AND p.deletedDate IS NULL AND p.quantityAvailable > 0")
    List<Product> findProductsByCategoryAndSubCategory(UUID categoryId, UUID subCategoryId);

}
