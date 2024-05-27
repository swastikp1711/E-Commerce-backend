package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminCategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c FROM Category c WHERE c.deletedDate IS NULL")
    List<Category> findAvailableCategory();

    @Query("SELECT c FROM Category c WHERE c.categoryId = :categoryId AND c.deletedDate IS NULL")
    Optional<Category> findAvailableCategoryById(UUID categoryId);

    @Query("SELECT c FROM Category c WHERE c.categoryName= :categoryName")
    Optional<Category> findCategoryByCategoryName(String categoryName);
}

