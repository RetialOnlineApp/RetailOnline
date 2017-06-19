package com.retail.services;

import java.util.UUID;


import com.retail.domains.Response;
import com.retail.entities.Marchant;
import com.retail.entities.MarchantAuth;
import com.retail.repositories.MarchantAuthRepository;
import com.retail.repositories.MarchantRepository;


public class MarchantService {
	
	
	MarchantRepository marchantRepository;
    MarchantAuthRepository authRepository;
	
	
	public MarchantService(MarchantRepository marchantRepository,
    MarchantAuthRepository authRepository) {
		this.marchantRepository = marchantRepository;
		this.authRepository = authRepository;
	}
	
	public Response marchantSignUp(Marchant marchant) {
		Response response = new Response();
		Marchant existingMarchant = marchantRepository.findByEmail(marchant.getEmail());
		if (existingMarchant == null) {
			Marchant createdMarchant = marchantRepository.save(marchant);
			marchantAccessToken(marchant);
			response.setStatus("201");
			response.setUserMessage("User Created with EmailId :: " + createdMarchant.getEmail());			
		}else {
			response.setStatus("500");
			response.setUserMessage("User Already exists with EmailId :: " + existingMarchant.getEmail());
		}
		return response;
		
	}
	
	private void marchantAccessToken(Marchant marchant) {
		String uniqueID = UUID.randomUUID().toString();
		MarchantAuth auth = new  MarchantAuth();
		auth.setuId(marchant.getId());
		auth.setAccessToken(uniqueID);
		authRepository.save(auth);
		
		
	}
	

}
