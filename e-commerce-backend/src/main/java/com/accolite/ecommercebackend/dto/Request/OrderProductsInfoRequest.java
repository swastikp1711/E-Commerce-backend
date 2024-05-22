package com.accolite.ecommercebackend.dto.Request;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderProductsInfoRequest {
    private UUID productId;

    private Integer quantity;

    private Double discountedPrice;
}
