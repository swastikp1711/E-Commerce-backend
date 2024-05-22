package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.OrderService;
import com.accolite.ecommercebackend.dto.Request.OrderDetailsRequest;
import com.accolite.ecommercebackend.dto.Request.OrderRequest;
import com.accolite.ecommercebackend.dto.Response.GetOrdersResponse;
import com.accolite.ecommercebackend.dto.Response.OrderDetailsInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        orderService.createOrder(orderRequest);
        return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<GetOrdersResponse> getUserOrders() {
        GetOrdersResponse ordersResponse = orderService.getUserOrders();
        return new ResponseEntity<>(ordersResponse, HttpStatus.OK);
    }

    @PostMapping("/details/create")
    public ResponseEntity<String> createOrderDetails(@RequestBody OrderDetailsRequest orderDetailsRequest) {
        orderService.createOrderDetails(orderDetailsRequest);
        return new ResponseEntity<>("Order details created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetailsInfoResponse> getOrderDetailsById(@PathVariable UUID orderId) {
        OrderDetailsInfoResponse orderDetails = orderService.getOrderDetailsById(orderId);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
}
