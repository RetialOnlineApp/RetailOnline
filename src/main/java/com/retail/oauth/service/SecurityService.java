package com.retail.oauth.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
}
