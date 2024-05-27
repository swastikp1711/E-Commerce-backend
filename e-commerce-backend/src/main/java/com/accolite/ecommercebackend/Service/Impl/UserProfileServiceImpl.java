package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.Repository.UserRepository;
import com.accolite.ecommercebackend.Service.UserProfileService;
import com.accolite.ecommercebackend.dto.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public UserProfileDto getUserProfile() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        return new UserProfileDto(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber()
        );
    }

    @Override
    public void updateUserProfile(UserProfileDto userProfileDto) {

        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);


        user.setEmail(userProfileDto.getEmail());
        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setPhoneNumber(userProfileDto.getPhoneNumber());
        user.setUpdatedDate(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public void deleteUserProfile() {
        String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userRepository.findByEmail(email);

        user.setDeletedDate(LocalDateTime.now());
        userRepository.save(user);
    }

}
