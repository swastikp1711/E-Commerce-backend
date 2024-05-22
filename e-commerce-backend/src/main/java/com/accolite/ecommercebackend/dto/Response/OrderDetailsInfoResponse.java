package com.accolite.ecommercebackend.dto.Response;

import com.accolite.ecommercebackend.Entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetailsInfoResponse {

    private LocalDateTime orderDate;

    private Double totalAmount;

    private Address orderAddress;

    private String orderStatus;

    private List<OrderProductCardInfoResponse> productCartInfo;
}
