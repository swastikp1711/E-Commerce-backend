package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllAddressesResponse {
    private List<AddressResponse> addresses;
}
