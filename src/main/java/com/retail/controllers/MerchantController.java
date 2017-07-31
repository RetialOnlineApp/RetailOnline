package com.retail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.domains.AccessTokenResponse;
import com.retail.domains.Response;
import com.retail.entities.MerchantAuth;
import com.retail.entities.MerchantProfile;
import com.retail.services.MerchantService;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {

	@Autowired
	MerchantService service;


	// Function will accept Merchant object in JSON format and will store it
	// into the
	// database
	@PostMapping("/signup")
	public ResponseEntity<Response> addMerchant(@RequestBody MerchantAuth merchant) {
		Response response = service.merchantSignUp(merchant);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// to verify Merchant with email verification
	@GetMapping("/signup/verify")
	public ResponseEntity<Response> verifyMerchant(@RequestParam String token) {
		Response response = service.verifyMerchant(token);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// returns accessToken for Merchant to validate other API'S
	@PostMapping("/accessToken")
	public ResponseEntity<AccessTokenResponse> accessToken(@RequestBody MerchantAuth merchant) {
		AccessTokenResponse response = service.accessToken(merchant);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// This function will accept MerchantProfile details in JSON and store it
	// into
	// the DataBase
	@PostMapping("/profile")
	public ResponseEntity<MerchantProfile> createProfile(@RequestBody MerchantProfile profile,
			@RequestHeader String accessToken) {
		MerchantProfile savedProfile = service.saveProfile(profile, accessToken);
		if (savedProfile != null) {
			return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(savedProfile, HttpStatus.UNAUTHORIZED);
	}

	// This function will gives MerchantProfile details which is stored in DB
	@GetMapping("/profile")
	public ResponseEntity<MerchantProfile> getProfile(@RequestHeader String accessToken) {
		MerchantProfile profile = service.getProfile(accessToken);
		if (profile != null) {
			return new ResponseEntity<>(profile, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(profile, HttpStatus.UNAUTHORIZED);
		}
	}

	// This function will logout the Merchant from Application
	@GetMapping("/logout")
	public ResponseEntity<Response> logout(@RequestHeader String accessToken) {
		Response response = service.logout(accessToken);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// This function will verify the email given by the merchant in forgot
	// password condition
	@GetMapping("/forgot")
	public ResponseEntity<Response> forgot(@RequestParam MerchantAuth merchantAuth) {
		Response response = service.verifyEmail(merchantAuth);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	// This function will accept the new Password, confirm it and save the new
	// Password in place of existed Password
	@PostMapping("/forgot/resetPassword")
	public ResponseEntity<Response> resetPassword(@RequestBody String newPassword, String confirmPassword) {
		Response response = service.savePassword(newPassword, confirmPassword);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

}
