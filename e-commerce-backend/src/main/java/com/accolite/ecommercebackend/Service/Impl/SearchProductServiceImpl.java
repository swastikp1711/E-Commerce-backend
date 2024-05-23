package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Product;
import com.accolite.ecommercebackend.Repository.SearchProductRepository;
import com.accolite.ecommercebackend.dto.Response.SearchProductInfoResponse;
import com.accolite.ecommercebackend.dto.Response.SearchProductResponse;
import com.accolite.ecommercebackend.Service.SearchProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SearchProductServiceImpl implements SearchProductService {

	@Autowired
	private SearchProductRepository searchProductRepository;

	@Override
	public SearchProductResponse searchProducts(String keyword, String category_name, String subcategory_name, Integer sortByPrice, Integer sortByDate) {
		List<Product> products = searchProductRepository.searchProducts(keyword, category_name, subcategory_name, sortByPrice, sortByDate);
		List<SearchProductInfoResponse> productDtos=new ArrayList<>();
		for(int i=0;i<products.size();i++){
			Product product=products.get(i);
			SearchProductInfoResponse productDto=new SearchProductInfoResponse(product.getProductId(),product.getImageUrl(),product.getTitle(),product.getSubtitle(),product.getBrand(),product.getQuantityAvailable(),product.getPrice(),product.getDiscountPercent(),product.getCategory().getCategoryName(),product.getSubCategory().getSubCategoryName());
			System.out.println(productDto);
			productDtos.add(productDto);
		}
		return new SearchProductResponse(productDtos);
	}
}