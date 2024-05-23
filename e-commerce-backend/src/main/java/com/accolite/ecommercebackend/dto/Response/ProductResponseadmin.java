package com.accolite.ecommercebackend.dto.Response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Setter
@Getter
public class ProductResponseadmin {

    private UUID productId;

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

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private LocalDateTime deletedDate;

    private String categoryName;

    private String subCategoryName;

}
