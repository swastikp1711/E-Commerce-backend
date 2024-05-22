package com.accolite.ecommercebackend.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginUserRequest {

    private String email;

    private String password;
}
