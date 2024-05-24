package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderDetailsInfoResponse {
    private String orderDate;
    private Double totalAmount;
    private AddressResponse orderAddress;
    private String orderStatus;
    private List<OrderProductCardInfoResponse> productCartInfo;
}

