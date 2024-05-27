package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.Entity.Orders;
import com.accolite.ecommercebackend.dto.Request.OrderDetailsRequest;
import com.accolite.ecommercebackend.dto.Request.OrderRequest;
import com.accolite.ecommercebackend.dto.Response.GetOrdersResponse;
import com.accolite.ecommercebackend.dto.Response.OrderDetailsInfoResponse;

import java.util.UUID;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);

    GetOrdersResponse getUserOrders();
    Orders findOrderbyId(UUID orderId);

    void createOrderDetails(OrderDetailsRequest orderDetailsRequest);

    OrderDetailsInfoResponse getOrderDetailsById(UUID orderId);
    GetOrdersResponse getOrders();
}
