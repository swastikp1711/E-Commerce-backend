package com.accolite.ecommercebackend.dto.Request;

import lombok.Data;

@Data
public class AddressRequest {

    private String address;
    private String city;
    private String state;
    private String name;
    private String phoneNumber;
    private String postalCode;
}
