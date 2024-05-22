package com.accolite.ecommercebackend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="productId", columnDefinition = "BINARY(16)")
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


    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "subCategoryId")
    private SubCategory subCategory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cart> carts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;

    public Product(UUID productId, String title, String imageUrl, String subtitle, String description, String productHighlights, String brand, Integer quantityAvailable, Double price, Double discountPercent, Double deliveryCharges, LocalDateTime createdDate, LocalDateTime updatedDate, LocalDateTime deletedDate, Category category, SubCategory subCategory) {
        this.productId = productId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.subtitle = subtitle;
        this.description = description;
        this.productHighlights = productHighlights;
        this.brand = brand;
        this.quantityAvailable = quantityAvailable;
        this.price = price;
        this.discountPercent = discountPercent;
        this.deliveryCharges = deliveryCharges;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.deletedDate = deletedDate;
        this.category = category;
        this.subCategory = subCategory;
    }
}

