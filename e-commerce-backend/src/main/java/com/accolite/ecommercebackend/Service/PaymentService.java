package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.Entity.Orders;

import java.util.UUID;

public interface PaymentService {
	void reduceProductQuantity(UUID orderId);

	void setStatus(Orders orders, String paymentId);

	void addPayment(Orders orders, String paymentId);
}
