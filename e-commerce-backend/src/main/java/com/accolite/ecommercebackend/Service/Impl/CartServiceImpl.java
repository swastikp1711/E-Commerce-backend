package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Cart;
import com.accolite.ecommercebackend.Entity.Product;
import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.Repository.CartRepository;
import com.accolite.ecommercebackend.Repository.ProductRepository;
import com.accolite.ecommercebackend.Repository.UserRepository;
import com.accolite.ecommercebackend.Service.CartService;
import com.accolite.ecommercebackend.dto.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String addCartItem(UUID productId) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));

        Cart cart = cartRepository.findByUserAndProduct(user, product);
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + 1);
        } else {
            cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(1);
        }

        cartRepository.save(cart);

        return "Product added to cart successfully";
    }

    @Override
    public void reduceCartItemQuantity(UUID cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart ID"));

        if (cart.getQuantity() > 1) {
            cart.setQuantity(cart.getQuantity() - 1);
            cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("Quantity must be greater than 1 to reduce");
        }
    }

    @Override
    public CartItemRemovedResponse removeCartItem(UUID cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart ID"));

        Integer quantityBeforeRemoval = cart.getQuantity();
        cartRepository.deleteItemById(cartId);


        return new CartItemRemovedResponse(quantityBeforeRemoval);
    }

    @Override
    public CartPageResponse getCartItems() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        List<Cart> cartItems = cartRepository.findByUser(user);

        List<CartPageResponse.CartItemInfoResponse> cartItemInfoResponses = cartItems.stream()
                .map(cart -> new CartPageResponse.CartItemInfoResponse(
                        cart.getCartId(),
                        cart.getProduct().getProductId(),
                        cart.getProduct().getImageUrl(),
                        cart.getProduct().getTitle(),
                        cart.getProduct().getSubtitle(),
                        cart.getProduct().getBrand(),
                        cart.getProduct().getPrice(),
                        cart.getProduct().getDiscountPercent(),
                        cart.getProduct().getDeliveryCharges(),
                        cart.getQuantity()
                ))
                .collect(Collectors.toList());
        return new CartPageResponse(cartItemInfoResponses);
    }

    @Override
    public CartItemUpdateResponse getCartItemsCount() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        User user = userRepository.findByEmail(email);

        Integer cartItemCount = cartRepository.sumQuantityByUser(user);

        if(cartItemCount==null) cartItemCount =0;
        return new CartItemUpdateResponse(cartItemCount);

    }

    @Override
    public void removeAllCartItem() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        cartRepository.deleteAllItemsByUserId(user.getUserId());
    }
}


/*
1. count cart items
2. list of cartItems product info object
    2.1 productI
    2.2 quantity
    2.3 discounted price
    2.4 delivery charges
    2.5 total amount
3. role


onHomePageLoad
1.categories, subCategories
2. min 10 products from 5-7 subcategoies
3. cartItems count

onCartPageLoad
1.productinfo
    2.1 productI
    2.2 quantity
    2.3 actual price
    2.4 discount percentage
    2.4 delivery charges
    2.5 title
    2.6 subtitle
    2.7 brand
    2.9 image





*/