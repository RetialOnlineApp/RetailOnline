/*This controller contains API for user registration , login and logout 
*/

package com.retail.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.domains.AccessTokenResponse;
import com.retail.domains.Response;
import com.retail.entities.UserAuth;
import com.retail.repositories.UserAuthRepository;
import com.retail.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	/*
	 * Auto wiring dependency here , so no need to create and initialize object
	 * spring boot will do it for us
	 */

	@Autowired
	UserAuthRepository userAuthRepository;
	
	// This service contains all the logic for user registration , login
	UserService service = new UserService();

	// Function will accept user object in JSON format and will store it in
	// database
	@PostMapping("/signup")
	public Response addUser(@RequestBody UserAuth user) {
		return service.userSignUp(user, userAuthRepository);
	}

	// to verify user with email verification
	@GetMapping("/signup/verify")
	public Response verifyUser(@RequestParam String token) {
		return service.verifyUser(token, userAuthRepository);
	}

	// returns accessToken for user to validate other API'S
	@PostMapping("/accessToken")
	public AccessTokenResponse accessToken(@RequestBody UserAuth user) {
		return service.accessToken(user, userAuthRepository);
	}

}
