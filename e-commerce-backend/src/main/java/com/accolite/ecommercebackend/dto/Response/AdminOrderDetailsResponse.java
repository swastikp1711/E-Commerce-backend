package com.accolite.ecommercebackend.dto.Response;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class AdminOrderDetailsResponse {
//    private UUID orderId;
//    private UUID productId;
//    private String productTitle;
//    private String productImage;
//    private Double unitPrice;
//    private Integer quantity;
//    private String orderStatus;
//    private double

    private UUID orderId;
    private String orderStatus;
//    private Double totalAmount;
    private Double totalPrice;
    private List<OrderDetailsResponse> orderDetails;

}
