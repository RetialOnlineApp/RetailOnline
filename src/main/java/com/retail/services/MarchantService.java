package com.retail.services;


import java.util.UUID;

import org.springframework.stereotype.Service;

import com.retail.domains.Response;
import com.retail.entities.MarchantAuth;
import com.retail.repositories.MarchantAuthRepository;
import com.retail.util.SignUpMailer;

@Service
public class MarchantService {
	
	public Response marchantSignUp(MarchantAuth marchant, MarchantAuthRepository marchantAuthRepository) {
		Response response = new Response();
		String verifyToken = UUID.randomUUID().toString();
		MarchantAuth existingMarchant = marchantAuthRepository.findByEmail(marchant.getEmail());
		if (existingMarchant == null) {
			marchant.setVerified(false);
			marchant.setVerifyToken(verifyToken);
			MarchantAuth createdMarchant = marchantAuthRepository.save(marchant);
			boolean mailStatus = sendVerificationMail(marchant, verifyToken);
			if (mailStatus) {
				response.setStatus("201");
				response.setUserMessage("Marchant Created with EmailId :: " + createdMarchant.getEmail() + 
						"  please check your mail for account activation link");	
			}else {
				response.setStatus("500");
				response.setUserMessage("invalid email..! Please check your mail once");	
			}
					
		}else {
			response.setStatus("500");
			response.setUserMessage("Marchant Already exists with EmailId :: " + existingMarchant.getEmail());
		}
		return response;
		
	}
	
	
	public Response verifyMarchant(String token, MarchantAuthRepository authRepository) {
		Response response = new Response();
		String accessToken = UUID.randomUUID().toString();
		MarchantAuth auth = authRepository.findByVerifyToken(token);
		if (auth != null) {
			auth.setVerified(true);
			auth.setAccessToken(accessToken);
			response.setUserMessage("Account verified.. Thanks for you time");
			response.setStatus("200");
		} else {
			response.setStatus("401");
			response.setUserMessage("Sorry ! Bad try..");
		}
		
		return response;
	}
	
	
	private boolean sendVerificationMail(MarchantAuth marchant,String verifyToken ) {
		SignUpMailer mailer = new SignUpMailer(); 
		boolean status = mailer.sendMailToMarchant(marchant.getEmail(), verifyToken);
		return status;
	}
	

}
