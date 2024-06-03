package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.AddressService;
import com.accolite.ecommercebackend.dto.Request.AddressRequest;
import com.accolite.ecommercebackend.dto.Response.AddressResponse;
import com.accolite.ecommercebackend.dto.Response.AllAddressesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("/getAll")
    public ResponseEntity<AllAddressesResponse> getAddressesForUser() {
        AllAddressesResponse allAddressesResponse = addressService.getAddressesForUser();
        return ResponseEntity.ok(allAddressesResponse);
    }

    @PostMapping("/add")
    public ResponseEntity<AddressResponse> addAddress(@RequestBody AddressRequest addressRequest) {
        ;
        return new ResponseEntity<>(addressService.saveAddress(addressRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity<String> updateAddress(@PathVariable UUID addressId,@RequestBody AddressRequest addressRequest) {
        addressService.updateAddress(addressId, addressRequest);
        return ResponseEntity.ok("Address updated successfully");
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<String> deleteAddress(@PathVariable UUID addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}

