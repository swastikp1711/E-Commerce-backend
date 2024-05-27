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

//{
//name: "skp",..
//phone: "6371949629",..
//line1: "Aditya Hyundai, Plot No 2132/5132",..
//landmark: "Hal Plot No 342/P, Lewis Road, Dist, BJB Nagar",
//city: "Bhubaneswar",
//state: "Odisha",
//pincode: "751014",
//type: "Home"
//        }
