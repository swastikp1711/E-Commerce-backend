package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.UserProfileDto;

import java.util.UUID;

public interface UserProfileService {
    UserProfileDto getUserProfile();

    void updateUserProfile(UserProfileDto userProfileDto);

    void deleteUserProfile();
}
