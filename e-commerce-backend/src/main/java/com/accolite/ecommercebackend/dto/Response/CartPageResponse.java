package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class CartPageResponse {
    private List<CartItemInfoResponse> cartItems;

    @Data
    @AllArgsConstructor
    public static class CartItemInfoResponse {

        private UUID cartId;

        private UUID productId;

        private String imageUrl;

        private String title;

        private String subTitle;

        private String brand;

        private Double price;

        private Double discountPercent;

        private Double deliveryCharge;

        private Integer quantity;
    }

}


