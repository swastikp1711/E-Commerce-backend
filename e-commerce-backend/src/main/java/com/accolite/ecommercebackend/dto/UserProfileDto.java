package com.accolite.ecommercebackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class UserProfileDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 1, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 50)
    private String lastName;

    @NotBlank
    @Size(min = 10, max = 15)
    private String phoneNumber;
}
