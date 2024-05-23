package com.accolite.ecommercebackend.Service.Impl;

import com.accolite.ecommercebackend.Service.OtpService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Properties;

// OtpService.java
@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Override
	public boolean sendOtp(String email, String otp) {
		try {
//			// Save OTP in the database
//			OtpEntity otpEntity = new OtpEntity();
//			otpEntity.setEmail(email);
//			otpEntity.setOtp(otp);
//			otpEntity.setExpiryTime(LocalDateTime.now().plusMinutes(5)); // OTP expiry in 5 minutes
//			otpRepository.save(otpEntity);

			// Send OTP via email
			sendEmail(email, "ShopIt Email Verification", "Your Otp for Email Verification is: "+otp);
			return true;
		}catch (Exception e){
			System.out.println(e);
			return false;
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
