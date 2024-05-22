package com.accolite.ecommercebackend.dto.Request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDetailsRequest {

    private UUID orderId;

    private List<OrderProductsInfoRequest> orderProductsInfo;



}
