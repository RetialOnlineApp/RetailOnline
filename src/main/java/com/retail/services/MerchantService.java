package com.retail.services;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.retail.domains.AccessTokenResponse;
import com.retail.domains.Response;
import com.retail.entities.MerchantAuth;
import com.retail.entities.MerchantProfile;
import com.retail.repositories.MerchantAuthRepository;
import com.retail.repositories.MerchantProfileRepository;
import com.retail.util.EmailService;
import com.retail.util.SecurityService;

@Service
public class MerchantService {

	@Autowired
	private EmailService emailService;

	@Autowired
	private MerchantAuthRepository authRepository;

	@Autowired
	private MerchantProfileRepository profileRepository;

	public Response merchantSignUp(MerchantAuth merchant) {
		Response response = new Response();
		String verifyToken = SecurityService.getAccessToken();
		try {

			MerchantAuth existingMarchant = authRepository.findByEmail(merchant.getEmail());
			if (existingMarchant == null) {
				String plainPassword = merchant.getPassword();
				String passwordHash = SecurityService.getMDHash(plainPassword);
				merchant.setPassword(passwordHash);
				merchant.setVerified(false);
				merchant.setVerifyToken(verifyToken);
				MerchantAuth createdMarchant = authRepository.save(merchant);
				boolean mailStatus = sendVerificationMail(merchant);
				if (mailStatus) {
					response.setStatus("201");
					response.setUserMessage("Marchant Created with EmailId :: " + createdMarchant.getEmail()
							+ "  please check your mail for account activation link");
				} else {
					authRepository.delete(merchant.getId());
					response.setStatus("500");
					response.setUserMessage("invalid email..! Please check your mail once");
				}

			} else {
				response.setStatus("500");
				response.setUserMessage("Marchant Already exists with EmailId :: " + existingMarchant.getEmail());
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return response;

	}

	public Response verifyMerchant(String token) {
		Response response = new Response();
		String accessToken = SecurityService.getAccessToken();
		MerchantAuth auth = authRepository.findByVerifyToken(token);
		if (auth != null && auth.isVerified() != true) {
			auth.setVerified(true);
			auth.setAccessToken(accessToken);
			authRepository.save(auth);
			response.setUserMessage("Account verified.. Thanks for you time");
			response.setStatus("200");
		} else {
			response.setStatus("401");
			response.setUserMessage("Sorry ! Bad try..");
		}

		return response;
	}

	public AccessTokenResponse accessToken(MerchantAuth merchant) {
		AccessTokenResponse response = new AccessTokenResponse();
		try{
		String passwordHash = SecurityService.getMDHash(merchant.getPassword());
		MerchantAuth auth = authRepository.findByEmailInAndPasswordIn(merchant.getEmail(), passwordHash);
		if (auth != null) {
			response.setAccessToken(auth.getAccessToken());
			response.setEmail(auth.getEmail());
			response.setDeveloperMSG("user message");
		} else {
			response.setDeveloperMSG("User not found");
		}}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	private boolean sendVerificationMail(MerchantAuth merchant) {
		boolean status = emailService.sendMailToMarchant(merchant.getEmail(), merchant.getVerifyToken());
		return status;
	}

	public MerchantProfile saveProfile(MerchantProfile profile, String accessToken) {
		MerchantAuth auth = authRepository.findByAccessToken(accessToken);
		if (auth != null) {
			profile.setMerchantId(auth.getId());
			return profileRepository.save(profile);
		}
		return null;

	}
	
	public Response logout(String accessToken) {
		Response response = new Response();
		MerchantAuth auth = authRepository.findByAccessToken(accessToken);
		if (auth != null) {
			String newAccessToken = SecurityService.getAccessToken();
			auth.setAccessToken(newAccessToken);
			authRepository.save(auth);
			response.setStatus("200");
			response.setUserMessage("user logout");
			}else {
				response.setStatus("404");
				response.setUserMessage("user not found");
			}
		return response;
	}
	
	public MerchantProfile getProfile(String accessToken) {
		MerchantProfile response = null;
		MerchantAuth auth = authRepository.findByAccessToken(accessToken);
		if (auth != null) {
			Integer merchantId = auth.getId();
			response = profileRepository.findByMerchantId(merchantId);
			}else {
				return null;
			}
		return response;
	}
}
