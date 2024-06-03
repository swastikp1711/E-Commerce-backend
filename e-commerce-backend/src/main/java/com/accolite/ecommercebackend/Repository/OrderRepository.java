package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID> {


    List<Orders> findByUserOrderByOrderDateDesc(User user);

    @Query("Select o from Orders o Where o.orderId=:orderId")
    Orders findbyOrderId(UUID orderId);



}
