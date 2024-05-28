package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Exception.IncorrectPasswordException;
import com.accolite.ecommercebackend.Exception.UserAlreadyExistException;
import com.accolite.ecommercebackend.Exception.UserNotFoundException;
import com.accolite.ecommercebackend.Service.AuthService;
import com.accolite.ecommercebackend.Service.ChangePasswordService;
import com.accolite.ecommercebackend.Service.OtpService;
import com.accolite.ecommercebackend.dto.Request.ChangePasswordRequest;
import com.accolite.ecommercebackend.dto.Request.LoginUserRequest;
import com.accolite.ecommercebackend.dto.Request.SendOtpRequest;
import com.accolite.ecommercebackend.dto.Request.SignUpUserRequest;
import com.accolite.ecommercebackend.dto.Response.ChangePasswordResponse;
import com.accolite.ecommercebackend.dto.Response.OtpResponse;
import com.accolite.ecommercebackend.dto.Response.UserAuthResponse;
import jakarta.validation.Valid;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private OtpService otpService;
    @Autowired
    private AuthService authService;

    @Autowired
    private ChangePasswordService changePasswordService;

//    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/auth/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpUserRequest signUpUserRequest) {
        try {
            UserAuthResponse response = this.authService.createUser(signUpUserRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (UserAlreadyExistException e) {
            return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth/sendotp")
    public ResponseEntity<OtpResponse> sendOtp(@Valid @RequestBody SendOtpRequest sendOtpRequest) {
        return otpService.sendOtp(sendOtpRequest.getEmail(),sendOtpRequest.isResend());
    }

    @PutMapping("/auth/reset/password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return changePasswordService.updatePassword(changePasswordRequest.getEmail(),changePasswordRequest.getPassword());
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        try {
            UserAuthResponse response = authService.login(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } catch (IncorrectPasswordException e) {
            return new ResponseEntity<>("Incorrect password", HttpStatus.UNAUTHORIZED);
        }
    }


}

