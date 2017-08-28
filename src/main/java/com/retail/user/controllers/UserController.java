package com.retail.user.controllers;

import com.retail.merchant.domains.AccessTokenResponse;
import com.retail.merchant.domains.Response;
import com.retail.user.entities.UserAuth;
import com.retail.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService service ;
	
	
	@PostMapping("/signup")
	public ResponseEntity<Response> addUser(@RequestBody UserAuth user) {
		Response response = service.userSignUp(user);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}

	// to verify user with email verification
	@GetMapping("/signup/verify")
	public ResponseEntity<Response> verifyUser(@RequestParam String token) {
		Response response = service.verifyUser(token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	// returns accessToken for user to validate other API'S
	@PostMapping("/accessToken")
	public ResponseEntity<AccessTokenResponse> accessToken(@RequestBody UserAuth user) {
		AccessTokenResponse response = service.accessToken(user);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	

}
