/*This controller contains API for user registration , login and logout 
*/

package com.retail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.retail.entities.MerchantProfile;
import com.retail.entities.UserAuth;
import com.retail.entities.UserProfile;
import com.retail.repositories.MerchantProfileRepository;
import com.retail.repositories.UserAuthRepository;
import com.retail.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService service ;

	MerchantProfileRepository merchantProfileRepository;
	
	
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
