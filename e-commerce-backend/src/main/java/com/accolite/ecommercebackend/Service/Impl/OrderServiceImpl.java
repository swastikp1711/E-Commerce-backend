package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.*;
import com.accolite.ecommercebackend.Repository.*;
import com.accolite.ecommercebackend.Service.OrderService;
import com.accolite.ecommercebackend.dto.Request.OrderDetailsRequest;
import com.accolite.ecommercebackend.dto.Request.OrderProductsInfoRequest;
import com.accolite.ecommercebackend.dto.Request.OrderRequest;
import com.accolite.ecommercebackend.dto.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public OrderResponse createOrder(OrderRequest orderRequest) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmailAndDeletedDateIsNull(email);

        System.out.println(orderRequest);
        Address address = addressRepository.findById(orderRequest.getAddressId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid address ID"));

        Orders order = new Orders();
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setDeliveryCharges(orderRequest.getDeliveryCharges());
        order.setTotalAmount(orderRequest.getTotalAmount());
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setAddress(address);

        Orders savedOrder = orderRepository.save(order);
        return new OrderResponse(savedOrder.getOrderId());
    }
    @Override
    public Orders findOrderbyId(UUID orderId) {
        Orders orders=orderRepository.findbyOrderId(orderId);
        return orders;
    }
    @Override
    public GetOrdersResponse getUserOrders() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByEmailAndDeletedDateIsNull(email);

        List<Orders> ordersList = orderRepository.findByUserOrderByOrderDateDesc(user);;

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
    public GetOrdersResponse getOrders() {
        List<Orders> ordersList = orderRepository.findAll();
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
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String formattedOrderDate = order.getOrderDate().format(formatter);


        List<OrderProductCardInfoResponse> productCardInfoResponses = order.getOrderDetails().stream()
                .map(this::mapToOrderProductCardInfoResponse)
                .collect(Collectors.toList());

        Address address = order.getAddress();
        AddressResponse addressResponse = new AddressResponse(
                address.getAddressId(),
                address.getAddress(),
                address.getCity(),
                address.getState(),
                address.getName(),
                address.getPhoneNumber(),
                address.getPostalCode(),
                address.getUser().getUserId()
        );

        return new OrderDetailsInfoResponse(
                formattedOrderDate,
                order.getTotalAmount(),
                addressResponse,
                order.getOrderStatus(),
                productCardInfoResponses
        );
    }

    private OrderProductCardInfoResponse mapToOrderProductCardInfoResponse(OrderDetail orderDetails) {


        return new OrderProductCardInfoResponse(
                orderDetails.getProduct().getImageUrl(),
                orderDetails.getProduct().getTitle(),
                orderDetails.getProduct().getSubtitle(),
                orderDetails.getProduct().getBrand(),
                orderDetails.getUnitPrice(),
                orderDetails.getQuantity()
        );
    }
}
