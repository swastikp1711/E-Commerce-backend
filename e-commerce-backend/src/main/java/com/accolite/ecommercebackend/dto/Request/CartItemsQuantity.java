package com.accolite.ecommercebackend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CartItemsQuantity {
    private UUID productId;
    private Integer quantity;

}
