package com.accolite.ecommercebackend.dto.Response;

import lombok.Data;

import java.util.UUID;
@Data
public class SearchProductInfoResponse {
	private UUID productId;

	private String imageUrl;

	private String title;

	private String subtitle;

	private String brand;

	private Integer quantityAvailable;

	private Double price;

	private Double discountPercent;

	private String categoryName;

	private String subCategoryName;

	public SearchProductInfoResponse(UUID productId, String imageUrl, String title, String subtitle, String brand, Integer quantityAvailable, Double price, Double discountPercent, String categoryName, String subCategoryName) {
		this.productId = productId;
		this.imageUrl = imageUrl;
		this.title = title;
		this.subtitle = subtitle;
		this.brand = brand;
		this.quantityAvailable = quantityAvailable;
		this.price = price;
		this.discountPercent = discountPercent;
		this.categoryName=categoryName;
		this.subCategoryName=subCategoryName;
	}

}
