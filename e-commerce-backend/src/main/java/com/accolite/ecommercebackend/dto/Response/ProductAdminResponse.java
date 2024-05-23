package com.accolite.ecommercebackend.dto.Response;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductAdminResponse {
    private UUID productId;
    @NotBlank(message = "imageurl cannot be none")
    private String imageUrl;

    @NotBlank(message = "title cannot be none")
    private String title;

    private Integer quantityAvailable;

    @NotBlank(message = "price cannot be none")
    private Double price;

    private String category;
    private String subCategory;
}
