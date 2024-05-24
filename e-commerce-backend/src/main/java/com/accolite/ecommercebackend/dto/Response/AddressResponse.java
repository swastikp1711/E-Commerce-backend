package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AddressResponse {
    private UUID addressId;
    private String address;
    private String city;
    private String state;
    private String name;
    private String phoneNumber;
    private String postalCode;
    private UUID userId;
}
