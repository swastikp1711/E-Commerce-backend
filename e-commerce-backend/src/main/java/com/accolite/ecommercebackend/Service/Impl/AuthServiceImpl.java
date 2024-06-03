package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.Exception.IncorrectPasswordException;
import com.accolite.ecommercebackend.Exception.UserAlreadyExistException;
import com.accolite.ecommercebackend.Exception.UserNotFoundException;
import com.accolite.ecommercebackend.Repository.UserRepository;
import com.accolite.ecommercebackend.Service.AuthService;
import com.accolite.ecommercebackend.dto.Request.LoginUserRequest;
import com.accolite.ecommercebackend.dto.Request.SignUpUserRequest;
import com.accolite.ecommercebackend.dto.Response.UserAuthResponse;
import com.accolite.ecommercebackend.security.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtHelper helper;


    @Override
    public UserAuthResponse createUser(SignUpUserRequest signUpUserRequest) {

        String requestEmail = signUpUserRequest.getEmail();

        if(userRepository.findByEmail(requestEmail)!=null){
            throw new UserAlreadyExistException("User Already Exist");
        }

        System.out.println("inside backend created user");


        LocalDateTime createdDate = LocalDateTime.now();

        User requestUser = User.builder().
                email(signUpUserRequest.getEmail())
                .password(passwordEncoder.encode(signUpUserRequest.getPassword()))
                .firstName(signUpUserRequest.getFirstName())
                .lastName(signUpUserRequest.getLastName())
                .phoneNumber(signUpUserRequest.getPhoneNumber())
                .role(signUpUserRequest.getRole())
                .createdDate(createdDate)
                .build();

        User createdUser = userRepository.save(requestUser);

        UserDetails userDetails = userDetailsService.loadUserByUsername(createdUser.getEmail());
        String token = this.helper.generateToken(userDetails);

        return new UserAuthResponse(token,
                createdUser.getUserId(),createdUser.getRole()
        );
    }

    @Override
    public UserAuthResponse login(LoginUserRequest request) {

        User user=userRepository.findByEmail(request.getEmail());

        if(user==null){
            throw new UserNotFoundException("User does not exist");
        }

        if(!passwordEncoder.matches( request.getPassword(), user.getPassword())){
            throw new IncorrectPasswordException("Incorrect Password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        final String token = helper.generateToken(userDetails);

        return new UserAuthResponse(token, user.getUserId(), user.getRole());
    }

    @Override
    public Boolean checkTokenExpiry(String authorizationHeader) {

        Boolean isExpired=false;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {


            String token = authorizationHeader.substring(7);

            isExpired = helper.isTokenExpired(token);

        }
        return isExpired;
    }
}
