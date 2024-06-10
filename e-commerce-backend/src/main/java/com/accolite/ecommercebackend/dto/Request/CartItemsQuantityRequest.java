package com.accolite.ecommercebackend.dto.Request;

import lombok.Data;

import java.util.List;

@Data
public class CartItemsQuantityRequest {
    private List<CartItemsQuantity> cartItemsQuantityDetails;
}
