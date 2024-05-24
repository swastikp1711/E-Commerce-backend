package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByDeletedDateIsNull();

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.subCategories sc WHERE c.deletedDate IS NULL AND sc.deletedDate IS NULL")
    List<Category> findAllActiveCategoriesAndSubCategories();
}
