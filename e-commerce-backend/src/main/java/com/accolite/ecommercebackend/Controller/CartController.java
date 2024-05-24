package com.accolite.ecommercebackend.Controller;


import com.accolite.ecommercebackend.Repository.CartRepository;
import com.accolite.ecommercebackend.Service.CartService;
import com.accolite.ecommercebackend.dto.Response.CartItemUpdateResponse;
import com.accolite.ecommercebackend.dto.Response.CartPageResponse;
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
    public ResponseEntity<String> addCartItem(@PathVariable UUID productId){
        return new ResponseEntity<>(cartService.addCartItem(productId), HttpStatus.CREATED);
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
