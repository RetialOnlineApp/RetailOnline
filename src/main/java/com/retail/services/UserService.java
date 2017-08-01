package com.retail.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.domains.AccessTokenResponse;
import com.retail.domains.Response;
import com.retail.entities.UserAuth;
import com.retail.entities.UserProfile;
import com.retail.repositories.UserAuthRepository;
import com.retail.repositories.UserProfileRepository;
import com.retail.util.EmailService;

@Service
public class UserService {
 
	@Autowired
	EmailService emailService;

	@Autowired
	UserProfileRepository profileRepo ;
	
	@Autowired
	UserAuthRepository userAuthRepository;
	
	

	public Response userSignUp(UserAuth user ) {
		Response response = new Response();
		String verifyToken = UUID.randomUUID().toString();
		UserAuth existingUser = userAuthRepository.findByEmail(user.getEmail());
		if (existingUser == null) {
			user.setVerified(false);
			user.setVerifyToken(verifyToken);
			UserAuth createdUser = userAuthRepository.save(user);
			boolean mailStatus = sendVerificationMail(user);
			if (mailStatus) {
				response.setStatus("201");
				response.setUserMessage("User Created with EmailId :: " + createdUser.getEmail()
						+ "  please check your mail for account activation link");
			} else {
				userAuthRepository.delete(user.getId());
				response.setStatus("500");
				response.setUserMessage("invalid email..! Please check your mail once");
			}

		} else {
			response.setStatus("500");
			response.setUserMessage("User Already exists with EmailId :: " + existingUser.getEmail());
		}
		return response;

	}

	public Response verifyUser(String token ) {
		Response response = new Response();
		String accessToken = UUID.randomUUID().toString();
		UserAuth auth = userAuthRepository.findByVerifyToken(token);
		if (auth != null && auth.isVerified() != true) {
			auth.setVerified(true);
			auth.setAccessToken(accessToken);
			userAuthRepository.save(auth);
			response.setUserMessage("Account verified.. Thanks for you time");
			response.setStatus("200");
		} else {
			response.setStatus("401");
			response.setUserMessage("Sorry ! Bad try..");
		}

		return response;
	}

	public AccessTokenResponse accessToken(UserAuth user) {
		AccessTokenResponse response = new AccessTokenResponse();
		UserAuth auth = userAuthRepository.findByEmailInAndPasswordIn(user.getEmail(), user.getPassword());
		if (auth != null && auth.isVerified()) {
			response.setAccessToken(auth.getAccessToken());
			response.setEmail(auth.getEmail());
			response.setDeveloperMSG("user message");
		} else {
			response.setDeveloperMSG("User not found");
		}
		return response;
	}

	private boolean sendVerificationMail(UserAuth user) {
		boolean status = emailService.sendMailToUser(user.getEmail(), user.getVerifyToken());
		return status;
	}

	public UserProfile userDetails(UserProfile profile) {
		return profileRepo.save(profile);
	}

}
