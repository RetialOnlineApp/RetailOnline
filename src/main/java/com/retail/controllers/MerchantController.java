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
	
	@PostMapping("/signup")
	public ResponseEntity<Response> addMerchant(@RequestBody MerchantAuth merchant) {
		Response response = service.merchantSignUp(merchant);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/signup/verify")
	public ResponseEntity<Response> verifyMerchant(@RequestParam String token) {
		Response response = service.verifyMerchant(token);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/accessToken")
	public ResponseEntity<AccessTokenResponse> accessToken(@RequestBody MerchantAuth merchant) {
		AccessTokenResponse response = service.accessToken(merchant);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/profile")
	public ResponseEntity<MerchantProfile> createMerchantProfile(@RequestBody MerchantProfile profile, @RequestHeader String accessToken) {
		MerchantProfile savedProfile = service.saveProfile(profile, accessToken);
		if (savedProfile != null) {
			return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(savedProfile, HttpStatus.NOT_FOUND);
	}

}
