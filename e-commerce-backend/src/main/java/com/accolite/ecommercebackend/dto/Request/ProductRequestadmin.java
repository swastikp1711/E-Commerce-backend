package com.accolite.ecommercebackend.dto.Request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductRequestadmin {
//    private UUID productId;

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

//    private Category category;
//
//    private SubCategory subCategory;

    private String category;
    private String subCategory;
}
