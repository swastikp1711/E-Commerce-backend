package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.dto.Response.AdminOrderDetailsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdminOrderDetailsRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findAllByOrderByOrderDateDesc();

}
