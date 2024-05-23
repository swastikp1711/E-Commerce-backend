package com.accolite.ecommercebackend.Service;

import jakarta.mail.MessagingException;

public interface OtpService {
	boolean sendOtp(String email,String otp);
	void sendEmail(String to, String subject, String text) throws MessagingException;
//	boolean verifyOtp(String email, String otp);
}
