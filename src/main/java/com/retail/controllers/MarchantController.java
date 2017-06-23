package com.retail.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retail.domains.Response;
import com.retail.entities.MarchantAuth;
import com.retail.repositories.MarchantAuthRepository;
import com.retail.services.MarchantService;

@RestController
@RequestMapping("/api/marchant")
public class MarchantController {
	
	@Autowired
    MarchantAuthRepository marchantAuthRepository;
	
	
	MarchantService service =  new MarchantService();
	
	@PostMapping("/signup")
	public Response addMarchant(@RequestBody MarchantAuth marchant) {
		return service.marchantSignUp(marchant, marchantAuthRepository);
		}
	
	@GetMapping("/signup/verify")
	public Response verifyMarchant(@RequestParam String verifyToken) {
		return service.verifyMarchant(verifyToken, marchantAuthRepository);		
	}
	
	

}
