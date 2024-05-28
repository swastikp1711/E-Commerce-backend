package com.accolite.ecommercebackend.dto.Response;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDetailsResponse {
//    private UUID orderDetailId;
    private UUID productId;
    private String productTitle;
    private String productImage;
//    private Double unitPrice;
//    private Integer quantity;
//    private double totalPrice;
}
