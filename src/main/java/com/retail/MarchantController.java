package com.retail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.domains.Response;
import com.retail.entities.Marchant;
import com.retail.repositories.MarchantAuthRepository;
import com.retail.repositories.MarchantRepository;
import com.retail.services.MarchantService;

@RestController
@RequestMapping("/marchant")
public class MarchantController {
	
	@Autowired
	MarchantRepository marchantRepository;
	@Autowired
    MarchantAuthRepository authRepository;
	
	
	MarchantService service =  new MarchantService(marchantRepository, authRepository);
	
	@PostMapping("/signup")
	public Response addUser(@RequestBody Marchant marchant) {
		return service.marchantSignUp(marchant);
		}
	
	

}
