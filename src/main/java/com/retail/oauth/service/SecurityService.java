package com.retail.oauth.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

@Service
public class SecurityService {

	public static String getMDHash(String plainText) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] bytesOfMessage = plainText.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(bytesOfMessage);
		String result = new String(digest);
		return result;
	}

	public static String getAccessToken() {
		String verifyToken = UUID.randomUUID().toString().replaceAll("-", "");
		return verifyToken;
	}

	public static int generateOTP() {
		Random random = new Random();
		int otp = 0;
		int length = 0;
		while (length != 4) {
			otp = random.nextInt(9990);
			length = (int) (Math.log10(otp) + 1);
		}
		return otp;
	}

}
