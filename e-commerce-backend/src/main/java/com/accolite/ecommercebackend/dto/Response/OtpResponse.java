package com.accolite.ecommercebackend.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class OtpResponse {

	private boolean success;
	private String message;

	private String otp;

	// Constructors, getters, and setters


}
