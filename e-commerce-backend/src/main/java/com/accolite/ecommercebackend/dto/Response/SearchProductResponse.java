package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SearchProductResponse {
	//	private List<Product> products;
	private String message="Successfully Fetched Data";

	private List<SearchProductInfoResponse> searchProductInfoResponses;

	public SearchProductResponse(List<SearchProductInfoResponse> searchProductInfoResponses) {
		this.searchProductInfoResponses = searchProductInfoResponses;
	}

	public SearchProductResponse(String message) {
		this.message = message;
	}
}