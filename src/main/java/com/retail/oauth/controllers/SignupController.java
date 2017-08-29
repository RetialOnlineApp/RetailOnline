package com.retail.oauth.controllers;

import com.retail.oauth.entities.User;
import com.retail.oauth.service.OauthService;
import com.retail.merchant.domains.AccessTokenResponse;
import com.retail.merchant.domains.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class SignupController {

	@Autowired
	OauthService oauthService;
	
	@PostMapping("/signup")
	public ResponseEntity<Response> addMerchant(@RequestBody User user) {
		Response response = oauthService.userSignUp(user);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/signup/verify")
	public ResponseEntity<Response> verifyUser(@RequestParam String token) {
		Response response = oauthService.verifyUser(token);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping("/accessToken")
	public ResponseEntity<AccessTokenResponse> accessToken(@RequestBody User user) {
		AccessTokenResponse response = oauthService.accessToken(user);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/logout")
	public ResponseEntity<Response> logout(@RequestHeader String accessToken) {
		Response response = oauthService.logout(accessToken);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/status")
	public ResponseEntity<Response> status(@RequestHeader String accessToken) {
		Response response = oauthService.getStatus(accessToken);
		if (response != null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
		}


	}
	


}