package com.retail.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
    UserAuthRepository userAuthRepository;
	
	
	UserService service =  new UserService();
	
	@PostMapping("/signup")
	public Response addUser(@RequestBody UserAuth user) {
		return service.userSignUp(user, userAuthRepository);
		}
	
	@GetMapping("/signup/verify")
	public Response verifyUser(@RequestParam String token) {
		return service.verifyUser(token, userAuthRepository);		
	}
	
	@PostMapping("/accessToken")
	public AccessTokenResponse accessToken(@RequestBody UserAuth user) {
		return service.accessToken(user, userAuthRepository);
		}
	
	

}
