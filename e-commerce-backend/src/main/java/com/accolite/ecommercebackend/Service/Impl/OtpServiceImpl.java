package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Entity.User;
import com.accolite.ecommercebackend.Repository.UserRepository;
import com.accolite.ecommercebackend.Service.OtpService;
import com.accolite.ecommercebackend.dto.Response.OtpResponse;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

// OtpService.java
@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private UserRepository userRepository;
	@Override
	public ResponseEntity<OtpResponse> sendOtp(String email, boolean resend) {
		try {
//			// Save OTP in the database
//			OtpEntity otpEntity = new OtpEntity();
//			otpEntity.setEmail(email);
//			otpEntity.setOtp(otp);
//			otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // OTP expiry in 5 minutes
//			otpRepository.save(otpEntity);
			Integer intotp=(int)(Math.random()*(800000)+100000);
			String otp=intotp.toString();
			if(resend){
				sendEmail(email, "ShopIt Email Verification", "Your Otp for Email Verification is: " + otp);
				return ResponseEntity.ok(new OtpResponse(true, "OTP sent successfully.", otp));
			}
			else {
				User user = userRepository.findByEmailAndDeletedDateIsNull(email);
				if (user != null) {
					// Send OTP via email
					sendEmail(email, "ShopIt Email Verification", "Your Otp for Email Verification is: " + otp);
					return ResponseEntity.ok(new OtpResponse(true, "OTP sent successfully.", otp));
				} else {
					return ResponseEntity.ok(new OtpResponse(false, "User does not exist", ""));
				}
			}

		}catch (Exception e){
			System.out.println(e);
			return ResponseEntity.ok(new OtpResponse(false, "Failed to send OTP. Please try again later.",""));
		}
	}
	@Override
	public void sendEmail(String to, String subject, String text) throws MessagingException {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setTo(to);
//		message.setSubject(subject);
//		message.setText(text);
//		javaMailSender.send(message);
		String username = "shopit.ecommerce.web@gmail.com";
		String password = "grhiygidebagygzj";
		String recipient = to;

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.from", "shopit.ecommerce.web@gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "587");
		props.setProperty("mail.debug", "true");

		Session session = Session.getInstance(props, null);
		MimeMessage msg = new MimeMessage(session);

		msg.setRecipients(Message.RecipientType.TO, recipient);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(text);
		Transport transport = session.getTransport("smtp");
		transport.connect(username, password);
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
	}
//	@Override
//	public boolean verifyOtp(String email, String otp) {
//		Optional<OtpEntity> otpEntityOptional = otpRepository.findByEmailAndOtp(email, otp);
//		if (otpEntityOptional.isPresent()) {
//			OtpEntity otpEntity = otpEntityOptional.get();
//			// Check if OTP is not expired
//			if (otpEntity.getExpiryTime().isAfter(LocalDateTime.now())) {
//				// OTP is valid and not expired
//				otpRepository.delete(otpEntity); // Optionally, delete the OTP entity after verification
//				return true;
//			}
//		}
//		return false;
//	}
}
