package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Category;
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

    List<SubCategory> findByCategoryCategoryNameAndDeletedDateIsNull(String categoryName);

    SubCategory findBySubCategoryNameAndDeletedDateNull(String subCategoryName);
}
