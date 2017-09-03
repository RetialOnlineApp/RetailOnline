package com.retail.merchant.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.retail.merchant.domains.Response;
import com.retail.merchant.entities.MerchantProfile;
import com.retail.merchant.repositories.MerchantProfileRepository;
import com.retail.oauth.entities.User;
import com.retail.oauth.repositories.UserAuthRepository;
import com.retail.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	@Autowired
	MerchantProfileRepository profileRepository;

	@Autowired
	OauthService oauthService;

	public ResponseEntity<MerchantProfile> saveProfile(MerchantProfile profile, String accessToken) {
		User user = oauthService.checkAccessToken(accessToken);
		if (user != null) {
			profile.setUserId(user.getId());
			profile.setEmail(user.getEmail());
			profile.setStatus("Active");
			MerchantProfile save = profileRepository.save(profile);
			return new ResponseEntity<>(save, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<MerchantProfile> getProfile(String accessToken) {
		User user = oauthService.checkAccessToken(accessToken);
		if (user != null) {
			MerchantProfile byMerchantId = profileRepository.findByUserId(user.getId());
			if (byMerchantId != null) {
				return new ResponseEntity<>(byMerchantId, HttpStatus.OK);
			}else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		}else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	public ResponseEntity<Response> updatePassword(String accessToken, JsonNode updatePassword) {

		String oldPassword = updatePassword.get("oldPassword").asText();
		String newPassword = updatePassword.get("newPassword").asText();
		Response response = oauthService.updatePassword(oldPassword, newPassword, accessToken);
		if (response.getStatus().equalsIgnoreCase("200")) {
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		}else {
			return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);
		}

	}

}
