package com.retail.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public boolean sendMailToUser(String to, String verifyToken) {
		String msg = "Please click on the below link to verify your account \n"
				+ "http://localhost:8080/api/user/signup/verify?token=" + verifyToken;
		if (isValidEmailAddress(to)) {
			return sendMail(to,"Retail Verification", msg);
		} else {
			return false;
		}
	}

	public boolean sendMailToMarchant(String to, String verifyToken) {
		String msg = "Please click on the below link to verify your account \n"
				+ "http://localhost:8080/api/marchant/signup/verify?token=" + verifyToken;
		if (isValidEmailAddress(to)) {
			return sendMail(to,"Retail Verification", msg);
		} else {
			return false;
		}

	}

	public boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public boolean sendMail(String toEmail, String subject, String message) {
		boolean status;
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(toEmail);
			mailMessage.setSubject(subject);
			mailMessage.setText(message);
			mailMessage.setFrom("admin@admin.com");
			javaMailSender.send(mailMessage);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}

		return status;
	}
}
