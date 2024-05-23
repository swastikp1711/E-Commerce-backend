package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.*;
import com.accolite.ecommercebackend.Repository.*;
import com.accolite.ecommercebackend.Service.OrderService;
import com.accolite.ecommercebackend.dto.Request.OrderDetailsRequest;
import com.accolite.ecommercebackend.dto.Request.OrderProductsInfoRequest;
import com.accolite.ecommercebackend.dto.Request.OrderRequest;
import com.accolite.ecommercebackend.dto.Response.GetOrdersResponse;
import com.accolite.ecommercebackend.dto.Response.OrderDetailsInfoResponse;
import com.accolite.ecommercebackend.dto.Response.OrderProductCardInfoResponse;
import com.accolite.ecommercebackend.dto.Response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByEmail(email);

        Address address = addressRepository.findById(orderRequest.getAddressId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid address ID"));

        Orders order = new Orders();
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setDeliveryCharges(orderRequest.getDeliveryCharges());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setAddress(address);

        orderRepository.save(order);
    }
    @Override
    public Orders findOrderbyId(UUID orderId) {
        Orders orders=orderRepository.findbyOrderId(orderId);
        return orders;
    }
    @Override
    public GetOrdersResponse getUserOrders() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByEmail(email);

        List<Orders> ordersList = orderRepository.findByUser(user);

        List<OrderResponse> orderResponses = ordersList.stream().map(order -> {
            List<String> productImages = order.getOrderDetails().stream()
                    .map(orderDetail -> orderDetail.getProduct().getImageUrl())
                    .collect(Collectors.toList());

            return new OrderResponse(
                    order.getOrderId(),
                    productImages,
                    order.getOrderStatus(),
                    order.getTotalAmount()
            );
        }).collect(Collectors.toList());

        return new GetOrdersResponse(orderResponses);
    }





    @Override
    public void createOrderDetails(OrderDetailsRequest orderDetailsRequest) {
        // Retrieve order using orderId from the request
        Orders order = orderRepository.findById(orderDetailsRequest.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderDetailsRequest.getOrderId()));

        for (OrderProductsInfoRequest productInfo : orderDetailsRequest.getOrderProductsInfo()) {
            Product product = productRepository.findById(productInfo.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productInfo.getProductId()));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrders(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(productInfo.getQuantity());
            orderDetail.setUnitPrice(productInfo.getDiscountedPrice());

            orderDetailRepository.save(orderDetail);
        }
    }

    @Override
    public OrderDetailsInfoResponse getOrderDetailsById(UUID orderId) {
        // Retrieve order using orderId
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        // Map OrderDetails to OrderProductCardInfoResponse
        List<OrderProductCardInfoResponse> productCardInfoResponses = order.getOrderDetails().stream()
                .map(this::mapToOrderProductCardInfoResponse)
                .collect(Collectors.toList());

        // Create and return the response
        return new OrderDetailsInfoResponse(
                order.getOrderDate(),
                order.getTotalAmount(),
                order.getAddress(),
                order.getOrderStatus(),
                productCardInfoResponses
        );
    }

    private OrderProductCardInfoResponse mapToOrderProductCardInfoResponse(OrderDetail orderDetail) {
        return new OrderProductCardInfoResponse(
                orderDetail.getProduct().getImageUrl(),
                orderDetail.getProduct().getTitle(),
                orderDetail.getProduct().getSubtitle(),
                orderDetail.getProduct().getBrand(),
                orderDetail.getUnitPrice(),
                orderDetail.getQuantity()
        );
    }
}
