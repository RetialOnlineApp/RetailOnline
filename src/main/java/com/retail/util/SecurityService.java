package com.retail.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
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
}
