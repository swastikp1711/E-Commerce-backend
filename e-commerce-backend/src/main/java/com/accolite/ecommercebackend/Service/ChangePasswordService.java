package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.Response.ChangePasswordResponse;
import org.springframework.http.ResponseEntity;

public interface ChangePasswordService {
	ResponseEntity<ChangePasswordResponse> updatePassword(String email, String password);
}
