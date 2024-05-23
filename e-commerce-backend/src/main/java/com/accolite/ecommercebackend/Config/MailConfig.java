package com.accolite.ecommercebackend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com"); // Update with your SMTP host
		mailSender.setPort(587); // Update with your SMTP port
		mailSender.setUsername("shopit.ecommerce.web@gmail.com"); // Update with your email
		mailSender.setPassword("grhiygidebagygzj"); // Update with your email password

		return mailSender;
	}
}
