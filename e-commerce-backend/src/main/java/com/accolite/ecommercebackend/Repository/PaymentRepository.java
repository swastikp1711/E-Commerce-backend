package com.accolite.ecommercebackend.Repository;

import com.accolite.ecommercebackend.Entity.OrderDetail;
import com.accolite.ecommercebackend.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
