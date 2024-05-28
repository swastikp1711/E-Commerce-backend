package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
	@Modifying
	@Transactional
	@Query("update Product p set p.quantityAvailable = p.quantityAvailable-(select quantity from OrderDetail od where od.orders.orderId=:orderId and od.product.productId=p.productId), p.updatedDate=:updatedDate where p.productId in (select od.product.productId from OrderDetail od where od.orders.orderId=:orderId)")
	void reduceQuantity(UUID orderId, LocalDateTime updatedDate);

}
