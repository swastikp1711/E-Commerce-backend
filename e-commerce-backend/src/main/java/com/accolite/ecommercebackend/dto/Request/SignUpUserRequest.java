package com.accolite.ecommercebackend.dto.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class SignUpUserRequest {

    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)")
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String role;

}
