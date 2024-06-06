package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Product;
import com.accolite.ecommercebackend.dto.Response.SalesDataResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SalesDataRepository extends JpaRepository<Product, UUID> {
//    @Query("SELECT new com.accolite.ecommercebackend.dto.Response.SalesDataResponse(" +
//            "(SELECT COUNT(p) FROM Product p), " +
//            "(SELECT COUNT(u) FROM User u), " +
//            "(SELECT COUNT(o) FROM Orders o), " +
//            "(SELECT SUM(o.totalAmount) FROM Orders o)) " +
//            "FROM Product p")
//        @Query(value = "SELECT " +
//        "(SELECT COUNT(*) FROM Product) AS productCount, " +
//        "(SELECT COUNT(*) FROM User) AS userCount, " +
//        "(SELECT COUNT(*) FROM Orders) AS orderCount, " +
//        "(SELECT COALESCE(SUM(total_amount), 0) FROM Orders) AS totalRevenue",
//        nativeQuery = true)
@Query("SELECT new com.accolite.ecommercebackend.dto.Response.SalesDataResponse(" +
        "(SELECT COUNT(p) FROM Product p), " +
        "(SELECT COUNT(u) FROM User u), " +
        "(SELECT COUNT(o) FROM Orders o), " +
        "(SELECT COALESCE(SUM(o.totalAmount), 0) FROM Orders o))")
    SalesDataResponse getSales();
}
