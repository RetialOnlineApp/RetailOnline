/*This controller contains API for user registration , login and logout 
*/

package com.retail.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.domains.AccessTokenResponse;
import com.retail.domains.Response;
import com.retail.entities.UserAuth;
import com.retail.entities.UserProfile;
import com.retail.repositories.UserAuthRepository;
import com.retail.repositories.UserProfileRepository;
import com.retail.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	/*
	 * Auto wiring dependency here , so no need to create and initialize object
	 * spring boot will do it for us
	 */

	UserAuthRepository userAuthRepository;

	UserProfileRepository userProfileRepository;
   
	// This service contains allsss the logic for user registration , login
	UserService service;

	public UserController(UserAuthRepository userAuthRepository, UserProfileRepository userProfileRepository,
			UserService service) {
		this.userAuthRepository = userAuthRepository;
		this.userProfileRepository = userProfileRepository;
		this.service = service;
	}

	// Function will accept user object in JSON format and will store it in
	// database
	@PostMapping("/signup")
	public ResponseEntity<Response> addUser(@RequestBody UserAuth user) {
		Response response = service.userSignUp(user, userAuthRepository);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}

	// to verify user with email verification
	@GetMapping("/signup/verify")
	public ResponseEntity<Response> verifyUser(@RequestParam String token) {
		Response response = service.verifyUser(token, userAuthRepository);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	// returns accessToken for user to validate other API'S
	@PostMapping("/accessToken")
	public ResponseEntity<AccessTokenResponse> accessToken(@RequestBody UserAuth user) {
		AccessTokenResponse response = service.accessToken(user, userAuthRepository);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/logout") public ResponseEntity<Response> logout() { String
	 * accessToken = ""; Response response = service.logout(accessToken,
	 * userAuthRepository); return new ResponseEntity<>(response,
	 * HttpStatus.OK); }
	 */

	// This function will accept UserProfile details in JSON and store it into
	// the DataBase
	@PostMapping("/profile")
	public ResponseEntity<UserProfile> createProfile(@RequestBody UserProfile profile) {
		profile = service.userDetails(profile);
		return new ResponseEntity<>(profile, HttpStatus.CREATED);
	}

}
