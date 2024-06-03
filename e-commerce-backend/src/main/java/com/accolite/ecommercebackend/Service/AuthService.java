package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.Request.LoginUserRequest;
import com.accolite.ecommercebackend.dto.Request.SignUpUserRequest;
import com.accolite.ecommercebackend.dto.Response.UserAuthResponse;

public interface AuthService {
    UserAuthResponse createUser(SignUpUserRequest signUpUserRequest);

    UserAuthResponse login(LoginUserRequest request);


    Boolean checkTokenExpiry(String authorizationHeader);
}
