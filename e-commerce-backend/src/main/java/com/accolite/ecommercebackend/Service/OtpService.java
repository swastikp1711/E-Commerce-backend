package com.accolite.ecommercebackend.Service;

import com.accolite.ecommercebackend.dto.Response.OtpResponse;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface OtpService {
	ResponseEntity<OtpResponse> sendOtp(String email, boolean resend);
	void sendEmail(String to, String subject, String text) throws MessagingException;
//	boolean verifyOtp(String email, String otp);
}
