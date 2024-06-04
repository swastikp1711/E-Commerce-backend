package com.accolite.ecommercebackend.Controller;


import com.accolite.ecommercebackend.Exception.ProductQuantityExceededException;
import com.accolite.ecommercebackend.Repository.CartRepository;
import com.accolite.ecommercebackend.Service.CartService;
import com.accolite.ecommercebackend.dto.Response.CartItemUpdateResponse;
import com.accolite.ecommercebackend.dto.Response.CartPageResponse;
import com.accolite.ecommercebackend.dto.Response.ExceptionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CartController {


    @Autowired
    private CartService cartService;


    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addCartItem(@PathVariable UUID productId) {
        try {
            String message = cartService.addCartItem(productId);
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } catch (ProductQuantityExceededException e) {
            return new ResponseEntity<>(new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ExceptionResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/reduce/{cartId}")
    public ResponseEntity<String> reduceCartItemQuantity(@PathVariable UUID cartId) {
        try {
            cartService.reduceCartItemQuantity(cartId);
            return new ResponseEntity<>("Item quantity reduced successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<?> removeCartItem(@PathVariable UUID cartId) {
        try {
            return new ResponseEntity<>(cartService.removeCartItem(cartId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/remove/all")
    public ResponseEntity<String> removeAllCartItem() {
            cartService.removeAllCartItem();
            return ResponseEntity.ok("User profile deleted successfully");
    }


    @GetMapping("/items")
    public ResponseEntity<CartPageResponse> getCartItems() {
        CartPageResponse cartPageResponse = cartService.getCartItems();
        return ResponseEntity.ok(cartPageResponse);
    }

    @GetMapping("/quantity")
    public ResponseEntity<CartItemUpdateResponse> getCartItemsCount() {
        System.out.println("inside controller getCartItemsCount");
        CartItemUpdateResponse response = cartService.getCartItemsCount();
        return ResponseEntity.ok(response);
    }
}
