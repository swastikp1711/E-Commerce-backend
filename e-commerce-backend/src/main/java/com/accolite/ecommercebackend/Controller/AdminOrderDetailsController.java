package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.AdminOrderDetailsService;
import com.accolite.ecommercebackend.dto.Request.UpdateOrderStatusRequest;
import com.accolite.ecommercebackend.dto.Request.UpdateProductRequest;
import com.accolite.ecommercebackend.dto.Response.AdminOrderDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderDetailsController {

    @Autowired
    private AdminOrderDetailsService adminOrderDetailsService;

    @GetMapping("/details")
    public List<AdminOrderDetailsResponse> getOrderDetails() {
        return adminOrderDetailsService.getAllOrders();
    }

    @PutMapping("/status/{orderId}")
    public void updateOrderStatus(@PathVariable UUID orderId,@RequestBody UpdateOrderStatusRequest request) {
        adminOrderDetailsService.updateOrderStatus(orderId,request);
    }

}
