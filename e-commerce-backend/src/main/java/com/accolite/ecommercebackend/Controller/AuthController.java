package com.accolite.ecommercebackend.Controller;

import com.accolite.ecommercebackend.Exception.IncorrectPasswordException;
import com.accolite.ecommercebackend.Exception.UserAlreadyExistException;
import com.accolite.ecommercebackend.Exception.UserNotFoundException;
import com.accolite.ecommercebackend.Service.AuthService;
import com.accolite.ecommercebackend.Service.OtpService;
import com.accolite.ecommercebackend.dto.Request.LoginUserRequest;
import com.accolite.ecommercebackend.dto.Request.SendOtpRequest;
import com.accolite.ecommercebackend.dto.Request.SignUpUserRequest;
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

    Integer otp;
    @Autowired
    private OtpService otpService;
    @Autowired
    private AuthService authService;

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

        otp= (int) (Math.random()*(800000)+100000);
        boolean otpSent = otpService.sendOtp(sendOtpRequest.getEmail(),otp.toString());
        if (otpSent) {
            return ResponseEntity.ok(new OtpResponse(true, "OTP has been sent successfully.",otp.toString()));
        } else {
            return ResponseEntity.ok(new OtpResponse(false, "Failed to send OTP. Please try again later.",""));
        }
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

