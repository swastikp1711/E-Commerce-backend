package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.OrderDetail;
import com.accolite.ecommercebackend.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID> {
}
