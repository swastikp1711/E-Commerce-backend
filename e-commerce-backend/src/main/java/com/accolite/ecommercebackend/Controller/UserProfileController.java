package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Service.UserProfileService;
import com.accolite.ecommercebackend.dto.UserProfileDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/profile/get")
    public ResponseEntity<UserProfileDto> getUserProfile() {
        UserProfileDto userProfile = userProfileService.getUserProfile();
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<String> updateUserProfile(@Valid @RequestBody UserProfileDto userProfileDto) {
        userProfileService.updateUserProfile(userProfileDto);
        return ResponseEntity.ok("User profile updated successfully");
    }

    @DeleteMapping("/profile/delete")
    public ResponseEntity<String> deleteUserProfile() {
        userProfileService.deleteUserProfile();
        return ResponseEntity.ok("User profile deleted successfully");
    }
}
