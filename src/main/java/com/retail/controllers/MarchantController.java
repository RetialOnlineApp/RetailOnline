/*This controller contains API for user registration , login and logout 
*/

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
import com.retail.entities.MarchantAuth;
import com.retail.repositories.MarchantAuthRepository;
import com.retail.services.MarchantService;

@RestController
@RequestMapping("/api/marchant")
public class MarchantController {

	/*
	 * Auto wiring dependency here , so no need to create and initialize object
	 * spring boot will do it for us
	 */

	@Autowired
	MarchantAuthRepository marchantAuthRepository;

	// This service contains all the logic for user registration , login
	MarchantService service = new MarchantService();

	// Function will accept user object in JSON format and will store it in
	// database
	@PostMapping("/signup")
	public Response addMarchant(@RequestBody MarchantAuth marchant) {
		return service.marchantSignUp(marchant, marchantAuthRepository);
	}

	// to verify user with email verification
	@GetMapping("/signup/verify")
	public Response verifyMarchant(@RequestParam String token) {
		return service.verifyMarchant(token, marchantAuthRepository);
	}

	// returns accessToken for user to validate other API'S
	@PostMapping("/accessToken")
	public AccessTokenResponse accessToken(@RequestBody MarchantAuth marchant) {
		return service.accessToken(marchant, marchantAuthRepository);
	}

}
