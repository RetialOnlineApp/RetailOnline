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
import com.retail.entities.UserAuth;
import com.retail.entities.UserProfile;
import com.retail.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService service;

	// Function will accept user object in JSON format and will store it into the
	// database
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

	// This function will accept UserProfile details in JSON and store it into
	// the DataBase
	@PostMapping("/profile")
	public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfile profile,
			@RequestHeader String accessToken) {
		UserProfile savedProfile = service.saveProfile(profile, accessToken);
		if (savedProfile != null) {
			return new ResponseEntity<>(profile, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(profile, HttpStatus.UNAUTHORIZED);
	}
	
	// This function will gives UserProfile details which is stored in DB
	@GetMapping("/profile")
	public ResponseEntity<UserProfile> getProfile(@RequestHeader String accessToken) {
		UserProfile profile = service.profileDetails(accessToken);
		if(profile != null){
			return new ResponseEntity<>(profile, HttpStatus.OK);
		}
		return new ResponseEntity<UserProfile>(profile, HttpStatus.UNAUTHORIZED);
	}
	
	// This function will logout the User from Application
	@GetMapping("/logout")
	public ResponseEntity<Response> logout(@RequestHeader String accessToken) {
		Response response = service.userLogout(accessToken);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
}
