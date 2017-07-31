package com.retail.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.domains.AccessTokenResponse;
import com.retail.domains.Response;
import com.retail.entities.UserAuth;
import com.retail.entities.UserProfile;
import com.retail.repositories.UserAuthRepository;
import com.retail.repositories.UserProfileRepository;
import com.retail.util.EmailService;
import com.retail.util.SecurityService;

@Service
public class UserService {

	@Autowired
	EmailService emailService;

	@Autowired
	UserProfileRepository profileRepo;

	@Autowired
	UserAuthRepository userAuthRepository;

	public Response userSignUp(UserAuth user)  {
		Response response = new Response();
		String verifyToken = SecurityService.getAccessToken();
		try{
		UserAuth existingUser = userAuthRepository.findByEmail(user.getEmail());
		if (existingUser == null) {
			String plainPassword = user.getPassword();
			String passwordHash = SecurityService.getMDHash(plainPassword);
			user.setPassword(passwordHash);
			user.setVerified(false);
			user.setVerifyToken(verifyToken);
			UserAuth createdUser = userAuthRepository.save(user);
			boolean mailStatus = sendVerificationMail(user, verifyToken);
			if (mailStatus) {
				response.setStatus("201");
				response.setUserMessage("User Created with EmailId :: " + createdUser.getEmail()
						+ "  please check your mail for account activation link");
			} else {
				response.setStatus("500");
				response.setUserMessage("invalid email..! Please check your mail once");
			}

		} else {
			response.setStatus("500");
			response.setUserMessage("User Already exists with EmailId :: " + existingUser.getEmail());
		}
		}catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			}
		return response;

	}

	public Response verifyUser(String token) {
		Response response = new Response();
		String accessToken = SecurityService.getAccessToken();
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

	private boolean sendVerificationMail(UserAuth user, String verifyToken) {
		boolean status = emailService.sendMailToUser(user.getEmail(), verifyToken);
		return status;
	}

	public UserProfile saveProfile(UserProfile profile, String accessToken) {
		UserAuth auth = userAuthRepository.findByAccessToken(accessToken);
		if (auth != null) {
			UserProfile existingprofile = profileRepo.findByUserId(auth.getId());
			if (existingprofile != null) {
				Integer id = existingprofile.getId();
				existingprofile = profile;
				existingprofile.setId(id);
				existingprofile.setUserId(auth.getId());
				UserProfile savedProfile = profileRepo.save(existingprofile);
				return savedProfile;

			} else {
				profile.setUserId(auth.getId());
				UserProfile savedProfile = profileRepo.save(profile);
				return savedProfile;
			}
		}
		return null;
	}

	public UserProfile profileDetails(String accessToken) {
		UserProfile profile = null;
		UserAuth auth = userAuthRepository.findByAccessToken(accessToken);
		if (auth != null) {
			Integer userId = auth.getId();
			profile = profileRepo.findByUserId(userId);
		} else {
			return null;
		}
		return profile;
	}

	public Response userLogout(String accessToken) {
		Response response = new Response();
		UserAuth auth = userAuthRepository.findByAccessToken(accessToken);
		if(auth != null){
			String newAccessToken = SecurityService.getAccessToken();
			auth.setAccessToken(newAccessToken);
			userAuthRepository.save(auth);
			response.setStatus("200");
			response.setUserMessage("User is Logout");
		}else{
			response.setStatus("404");
			response.setUserMessage("User not Found");
		}
		return response;
	}
}
