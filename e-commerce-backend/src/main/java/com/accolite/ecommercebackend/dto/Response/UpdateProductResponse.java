package com.accolite.ecommercebackend.dto.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductResponse {
    private Integer quantityAvailable;

    private Double price;

    private Double discountPercent;

    private Double deliveryCharges;
}
