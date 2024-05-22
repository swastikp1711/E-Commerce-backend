package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderResponse {

    private UUID orderId;

    private List<String> productImages;

    private String deliveryStatus;

    private Double totalAmount;


}
