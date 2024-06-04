package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.Address;
import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.Repository.AddressRepository;
import com.accolite.ecommercebackend.Repository.UserRepository;
import com.accolite.ecommercebackend.Service.AddressService;
import com.accolite.ecommercebackend.dto.Request.AddressRequest;
import com.accolite.ecommercebackend.dto.Response.AddressResponse;
import com.accolite.ecommercebackend.dto.Response.AllAddressesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AllAddressesResponse getAddressesForUser() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmailAndDeletedDateIsNull(email);


        List<Address> addresses = addressRepository.findByUserAndDeletedDateIsNull(user);

        List<AddressResponse> addressResponses = addresses.stream()
                .map(address -> new AddressResponse(
                        address.getAddressId(),
                        address.getAddress(),
                        address.getCity(),
                        address.getState(),
                        address.getName(),
                        address.getPhoneNumber(),
                        address.getPostalCode(),
                        user.getUserId()
                ))
                .collect(Collectors.toList());

        return new AllAddressesResponse(addressResponses);
    }

    @Override
    public AddressResponse saveAddress(AddressRequest addressRequest) {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmailAndDeletedDateIsNull(email);

        Address address = new Address();
        address.setAddress(addressRequest.getAddress());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setName(addressRequest.getName());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setPostalCode(addressRequest.getPostalCode());
        address.setCreatedDate(LocalDateTime.now());
        address.setUser(user);

        addressRepository.save(address);

        return new AddressResponse(
                address.getAddressId(),
                address.getAddress(),
                address.getCity(),
                address.getState(),
                address.getName(),
                address.getPhoneNumber(),
                address.getPostalCode(),
                user.getUserId()
        );
    }

    @Override
    public void updateAddress(UUID addressId, AddressRequest addressRequest) {

        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setAddress(addressRequest.getAddress());
            address.setCity(addressRequest.getCity());
            address.setState(addressRequest.getState());
            address.setName(addressRequest.getName());
            address.setPhoneNumber(addressRequest.getPhoneNumber());
            address.setPostalCode(addressRequest.getPostalCode());
            address.setUpdatedDate(LocalDateTime.now());

            addressRepository.save(address);
        } else {
            throw new IllegalArgumentException("Address not found for the user");
        }
    }

    @Override
    public void deleteAddress(UUID addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);

        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            address.setDeletedDate(LocalDateTime.now());
            addressRepository.save(address);
        } else {
            throw new IllegalArgumentException("Address not found with ID: " + addressId);
        }
    }
}
