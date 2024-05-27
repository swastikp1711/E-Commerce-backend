package com.accolite.ecommercebackend.dto.Request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {
    private Integer quantityAvailable;

    private Double price;

    private Double discountPercent;


    private Double deliveryCharges;
}
