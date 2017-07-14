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

	MarchantAuthRepository marchantAuthRepository;
	MarchantService service;

	@Autowired
	public MarchantController(MarchantAuthRepository marchantAuthRepository, MarchantService service) {
		this.marchantAuthRepository = marchantAuthRepository;
		this.service = service;
	}

	// Function will accept user object in JSON format and will store it in
	// database
	@PostMapping("/signup")
	public ResponseEntity<Response> addMarchant(@RequestBody MarchantAuth marchant) {
		Response response = service.marchantSignUp(marchant, marchantAuthRepository);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	// to verify user with email verification
	@GetMapping("/signup/verify")
	public ResponseEntity<Response> verifyMarchant(@RequestParam String token) {
		Response response = service.verifyMarchant(token, marchantAuthRepository);
		return new ResponseEntity<>(response,HttpStatus.OK);

	}

	// returns accessToken for user to validate other API'S
	@PostMapping("/accessToken")
	public ResponseEntity<AccessTokenResponse> accessToken(@RequestBody MarchantAuth marchant) {
		AccessTokenResponse response =  service.accessToken(marchant, marchantAuthRepository);
		return new ResponseEntity<>(response,HttpStatus.OK);

	}

}
