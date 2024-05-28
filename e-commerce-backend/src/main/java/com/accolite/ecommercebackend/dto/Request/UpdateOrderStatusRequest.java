package com.accolite.ecommercebackend.dto.Request;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateOrderStatusRequest {
//    private UUID orderId;
    private String orderStatus;
}
