package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.dto.Request.OrderDetailsRequest;
import com.accolite.ecommercebackend.dto.Request.OrderRequest;
import com.accolite.ecommercebackend.dto.Response.GetOrdersResponse;
import com.accolite.ecommercebackend.dto.Response.OrderDetailsInfoResponse;
import com.accolite.ecommercebackend.dto.Response.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(OrderRequest orderRequest);

    GetOrdersResponse getUserOrders();
    Orders findOrderbyId(UUID orderId);

    void createOrderDetails(OrderDetailsRequest orderDetailsRequest);

    OrderDetailsInfoResponse getOrderDetailsById(UUID orderId);
    GetOrdersResponse getOrders();

    List<Orders> getPendingOrders();
}
