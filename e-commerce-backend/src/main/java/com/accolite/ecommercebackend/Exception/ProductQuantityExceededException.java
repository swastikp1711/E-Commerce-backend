package com.accolite.ecommercebackend.Exception;

public class ProductQuantityExceededException extends RuntimeException {
    public ProductQuantityExceededException(String message) {
        super(message);
    }
}