package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Cart;
import com.accolite.ecommercebackend.Entity.Category;
import com.accolite.ecommercebackend.Entity.SubCategory;
import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.Repository.CartRepository;
import com.accolite.ecommercebackend.Repository.CategoryRepository;
import com.accolite.ecommercebackend.Repository.UserRepository;
import com.accolite.ecommercebackend.Service.HomePageService;
import com.accolite.ecommercebackend.dto.Response.HomePageLoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public HomePageLoadResponse loadHomePage() {
        List<Category> categories = categoryRepository.findByDeletedDateIsNull();

        List<SubCategory> subCategories = categories.stream()
                .flatMap(category -> category.getSubCategories().stream())
                .filter(subCategory -> subCategory.getDeletedDate() == null)
                .filter(subCategory -> subCategory.getProducts().stream().filter(product -> product.getDeletedDate() == null).count() > 10)
                .toList();

        List<HomePageLoadResponse.SubCategoryInfo> subCategoryInfos = subCategories.stream()
                .collect(Collectors.groupingBy(SubCategory::getCategory))
                .values().stream()
                .flatMap(list -> list.stream().limit(1))
                .limit(5)
                .map(subCategory -> {
                    List<HomePageLoadResponse.ProductInfo> productInfos = subCategory.getProducts().stream()
                            .filter(product -> product.getDeletedDate() == null)
                            .limit(10)
                            .map(product -> new HomePageLoadResponse.ProductInfo(
                                    product.getProductId(),
                                    product.getImageUrl(),
                                    product.getTitle(),
                                    product.getSubtitle(),
                                    product.getBrand(),
                                    product.getPrice(),
                                    product.getDiscountPercent()
                            ))
                            .collect(Collectors.toList());

                    return new HomePageLoadResponse.SubCategoryInfo(
                            subCategory.getSubCategoryId(),
                            subCategory.getSubCategoryName(),
                            productInfos
                    );
                })
                .collect(Collectors.toList());


        return new HomePageLoadResponse(subCategoryInfos);
    }


}


