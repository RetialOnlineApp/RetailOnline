package com.retail.services;



import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.retail.domains.AccessTokenResponse;
import com.retail.domains.Response;
import com.retail.entities.MerchantAuth;
import com.retail.entities.MerchantProfile;
import com.retail.repositories.MerchantAuthRepository;
import com.retail.repositories.MerchantProfileRepository;
import com.retail.util.EmailService;

@Service
public class MerchantService {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private MerchantAuthRepository authRepository;
	
	@Autowired
	private MerchantProfileRepository merchantProfileRepository;
	
	public Response merchantSignUp(MerchantAuth marchant) {
		Response response = new Response();
		String verifyToken = UUID.randomUUID().toString();
		MerchantAuth existingMarchant = authRepository.findByEmail(marchant.getEmail());
		if (existingMarchant == null) {
			marchant.setVerified(false);
			marchant.setVerifyToken(verifyToken);
			MerchantAuth createdMarchant = authRepository.save(marchant);
			boolean mailStatus = sendVerificationMail(marchant, verifyToken);
			if (mailStatus) {
				response.setStatus("201");
				response.setUserMessage("Marchant Created with EmailId :: " + createdMarchant.getEmail()
						+ "  please check your mail for account activation link");
			} else {
				response.setStatus("500");
				response.setUserMessage("invalid email..! Please check your mail once");
			}

		} else {
			response.setStatus("500");
			response.setUserMessage("Marchant Already exists with EmailId :: " + existingMarchant.getEmail());
		}
		return response;

	}

	public Response verifyMerchant(String token) {
		Response response = new Response();
		String accessToken = UUID.randomUUID().toString();
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
		MerchantAuth auth = authRepository.findByEmailInAndPasswordIn(merchant.getEmail(), merchant.getPassword());
		if (auth != null) {
			response.setAccessToken(auth.getAccessToken());
			response.setEmail(auth.getEmail());
			response.setDeveloperMSG("user message");
		} else {
			response.setDeveloperMSG("User not found");
		}
		return response;
	}

	private boolean sendVerificationMail(MerchantAuth merchant, String verifyToken) {
		boolean status = emailService.sendMailToMarchant(merchant.getEmail(), verifyToken);
		return status;
	}
	
	public MerchantProfile saveProfile(MerchantProfile profile, String accessToken){
		MerchantAuth auth = authRepository.findByAccessToken(accessToken);
		if (auth != null) {
			profile.setMerchantId(auth.getId());
			MerchantProfile savedProfile = merchantProfileRepository.save(profile);
			return savedProfile;
		}
		return null;
		
	}

}
