package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderProductCardInfoResponse {

    private String imgURL;

    private String title;

    private String subTitle;

    private String brand;

    private Double discountedPrice;

    private Integer quantity;
}
