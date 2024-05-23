package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.Response.SearchProductResponse;

import java.util.List;
import java.util.UUID;

public interface SearchProductService {
	SearchProductResponse searchProducts(String keyword, String category_name, String subcategory_name, Integer sortByPrice, Integer sortByDate);
}