package com.accolite.ecommercebackend.dto.Request;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderRequest {

    private String orderStatus;

    private Double deliveryCharges;

    private Double totalAmount;

    private UUID userId;

    private UUID addressId;
}
