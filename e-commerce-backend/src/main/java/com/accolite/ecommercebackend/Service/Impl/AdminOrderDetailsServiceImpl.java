package com.accolite.ecommercebackend.Service.Impl;
import com.accolite.ecommercebackend.Repository.AdminOrderDetailsRepository;
import com.accolite.ecommercebackend.Repository.OrderRepository;
import com.accolite.ecommercebackend.dto.Request.UpdateOrderStatusRequest;
import com.accolite.ecommercebackend.dto.Response.AdminOrderDetailsResponse;
import com.accolite.ecommercebackend.Entity.OrderDetail;
import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.Service.AdminOrderDetailsService;
import com.accolite.ecommercebackend.dto.Response.OrderDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminOrderDetailsServiceImpl implements AdminOrderDetailsService{

        @Autowired
        private AdminOrderDetailsRepository adminOrderDetailsRepository;

        @Autowired
        private OrderRepository orderRepository;

    @Override
    public List<AdminOrderDetailsResponse> getAllOrders() {
        List<Orders> ordersList = adminOrderDetailsRepository.findAllByOrderByOrderDateDesc();
        return ordersList.stream().map(this::convertToOrderResponse).collect(Collectors.toList());
    }

    private AdminOrderDetailsResponse convertToOrderResponse(Orders order) {
        AdminOrderDetailsResponse response = new AdminOrderDetailsResponse();
        response.setOrderId(order.getOrderId());
        response.setOrderStatus(order.getOrderStatus());
        response.setTotalPrice(order.getTotalAmount());
        response.setOrderDetails(order.getOrderDetails().stream().map(this::convertToOrderDetailResponse).collect(Collectors.toList()));
        return response;
    }

    private OrderDetailsResponse convertToOrderDetailResponse(OrderDetail orderDetail) {
        OrderDetailsResponse response = new OrderDetailsResponse();
//        response.setOrderDetailId(orderDetail.getOrderDetailId());
        response.setProductId(orderDetail.getProduct().getProductId());
        response.setProductTitle(orderDetail.getProduct().getTitle());
        response.setProductImage(orderDetail.getProduct().getImageUrl());
        return response;
    }

    @Override
    public void updateOrderStatus(UUID orderId, UpdateOrderStatusRequest request) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setOrderStatus(request.getOrderStatus());
        String status = request.getOrderStatus();
        System.out.println(status);
        if(status != null && status.equals("Shipped"))
            order.setShipped(LocalDateTime.now());
        if(status != null && status.equals("Delivered"))
            order.setDelivered(LocalDateTime.now());
        adminOrderDetailsRepository.save(order);
    }
}

