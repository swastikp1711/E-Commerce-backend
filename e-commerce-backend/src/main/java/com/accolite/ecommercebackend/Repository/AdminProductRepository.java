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

    @Query("SELECT p FROM Product p WHERE p.deletedDate IS NULL AND p.quantityAvailable > 0 order by createdDate Desc")
    List<Product> findAvailableProducts();

    @Query("SELECT p FROM Product p WHERE p.productId = :productId AND p.deletedDate IS NULL AND p.quantityAvailable > 0")
    Optional<Product> findByProductId(UUID productId);

    Optional<Product> findByProductIdAndDeletedDateIsNullAndQuantityAvailableGreaterThan(UUID productId, int quantity);

}
