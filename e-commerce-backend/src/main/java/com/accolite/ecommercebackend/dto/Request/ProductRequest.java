package com.accolite.ecommercebackend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
public class ProductRequest {

    private String imageUrl;

    private String title;

    private String subtitle;

    private String description;

    private String productHighlights;

    private String brand;

    private Integer quantityAvailable;

    private Double price;

    private Double discountPercent;

    private Double deliveryCharges;

    private UUID categoryId;

    private UUID subCategoryId;
}
