package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.dto.Response.SearchProductResponse;
import com.accolite.ecommercebackend.Service.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CacheConfig(cacheNames = "searchproduct")
public class SearchProductController {

	@Autowired
	private SearchProductService searchProductService;

	@GetMapping("/auth/SearchProduct")
	public ResponseEntity<SearchProductResponse> searchProduct(
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String subcategory,
			@RequestParam(required = false) Integer sortByPrice,
			@RequestParam(required = false) Integer sortByDate) {
		try {
			SearchProductResponse response = searchProductService.searchProducts(keyword, category, subcategory, sortByPrice, sortByDate);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new SearchProductResponse("Error occurred while searching products"), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}