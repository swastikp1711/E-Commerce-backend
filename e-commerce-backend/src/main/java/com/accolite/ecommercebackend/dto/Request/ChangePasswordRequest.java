package com.accolite.ecommercebackend.dto.Request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
	private String email;
	private String password;
}
