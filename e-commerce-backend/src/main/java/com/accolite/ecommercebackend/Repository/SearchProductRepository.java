package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SearchProductRepository extends JpaRepository<Product, UUID> {
	@Query("SELECT p FROM Product p " +
			"WHERE (:keyword IS NULL OR p.title LIKE %:keyword%) " +
			"AND (:category_name IS NULL OR p.category.categoryName = :category_name) " +
			"AND (:subcategory_name IS NULL OR p.subCategory.subCategoryName = :subcategory_name) " +
			"AND (p.quantityAvailable>0) "+
			"AND (p.deletedDate IS NULL) "+
			"ORDER BY " +
			"CASE WHEN :sortByPrice = 0 THEN p.price END ASC, " +
			"CASE WHEN :sortByPrice = 1 THEN p.price END DESC, " +
			"CASE WHEN :sortByDate = 0 THEN p.createdDate END ASC, " +
			"CASE WHEN :sortByDate = 1 THEN p.createdDate END DESC")
	List<Product> searchProducts(String keyword, String category_name, String subcategory_name, Integer sortByPrice, Integer sortByDate);
}