package com.accolite.ecommercebackend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class CartItemRequest {

    private UUID productId;

}
