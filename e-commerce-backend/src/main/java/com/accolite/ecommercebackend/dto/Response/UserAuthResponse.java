package com.accolite.ecommercebackend.dto.Response;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserAuthResponse {

    private String jwtToken;

    private UUID userId;

    private String role;

}
