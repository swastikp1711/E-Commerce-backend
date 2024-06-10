package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.Entity.Payment;
import com.accolite.ecommercebackend.Repository.OrderRepository;
import com.accolite.ecommercebackend.Repository.PaymentRepository;
import com.accolite.ecommercebackend.Repository.ProductRepository;
import com.accolite.ecommercebackend.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	@Override
	public void reduceProductQuantity(UUID orderId){
		LocalDateTime date=LocalDateTime.now();
		productRepository.reduceQuantity(orderId,date );
	}

	@Override
	public void restoreProductQuantity(UUID orderId){
		LocalDateTime date=LocalDateTime.now();
		productRepository.restoreQuantity(orderId,date );
	}


	@Override
	public void setStatus(Orders orders, String paymentId){
		Optional<Payment> payment=paymentRepository.findById(paymentId);
		payment.ifPresent(value -> value.setPaymentStatus("Completed"));
		orders.setOrderStatus("Placed");
		orderRepository.save(orders);
	}


	@Override
	public void addPayment(Orders orders, String paymentId){
		Payment payment=new Payment();
		payment.setPaymentStatus("Success");
		payment.setPaymentId(paymentId);
		payment.setPaymentDate(LocalDateTime.now());
		payment.setOrders(orders);
		payment.setAmount(orders.getTotalAmount());
		paymentRepository.save(payment);

	}
}
