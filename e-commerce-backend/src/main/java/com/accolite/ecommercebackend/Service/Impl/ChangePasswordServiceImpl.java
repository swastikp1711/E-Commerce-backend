package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.Repository.UserRepository;
import com.accolite.ecommercebackend.Service.ChangePasswordService;
import com.accolite.ecommercebackend.dto.Response.ChangePasswordResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public ResponseEntity<ChangePasswordResponse> updatePassword(String email, String password){
		try {
			User user = userRepository.findByEmailAndDeletedDateIsNull(email);
			userRepository.updatePassword(email, passwordEncoder.encode(password), LocalDateTime.now());
			return ResponseEntity.ok(new ChangePasswordResponse("Password Updated Successfully"));
		}catch (Exception e){
			return ResponseEntity.ok(new ChangePasswordResponse(e.getMessage()));
		}
	}
}
