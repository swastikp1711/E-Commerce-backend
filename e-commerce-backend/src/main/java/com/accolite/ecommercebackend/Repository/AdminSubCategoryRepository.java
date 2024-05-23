package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminSubCategoryRepository extends JpaRepository<SubCategory, UUID> {
    @Query("SELECT s FROM SubCategory s WHERE s.deletedDate IS NULL")
    List<SubCategory> findAvailableSubcategory();

    @Query("SELECT s FROM SubCategory s WHERE s.subCategoryId = :subCategoryId AND s.deletedDate IS NULL")
    Optional<SubCategory> findAvailableSubcategoryById(UUID subCategoryId);

    //    @Query("SELECT s FROM SubCategory s WHERE s.categoryId IN (SELECT c FROM Category c WHERE c.categoryName = :categoryName)")
    @Query("SELECT s FROM SubCategory s WHERE s.category.categoryName = :categoryName")
    List<SubCategory> findbyCategoryByName(String categoryName);


//    select subcatname from subcategory s where s.catid in (select catid from category c where c.catname = categoryname)
//    @Query("SELECT s FROM SubCategory s WHERE s.category.categoryId =: categoryId AND s.deletedDate IS NULL")
//    List<SubCategory> findAvailableSubcategoryByCategoryId(@Param("categoryId") UUID categoryId);
}
