package com.retail.services;

import java.util.UUID;


import com.retail.domains.Response;
import com.retail.entities.User;
import com.retail.entities.UserAuth;
import com.retail.repositories.UserAuthRepository;
import com.retail.repositories.UserRepository;

public class UserService {
	
	
	UserRepository userRepository;
    UserAuthRepository userAuthRepository;
	
	
	public UserService(UserRepository userRepository,
    UserAuthRepository userAuthRepository) {
		this.userRepository = userRepository;
		this.userAuthRepository = userAuthRepository;
	}
	
	public Response userSignUp(User user) {
		Response response = new Response();
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser == null) {
			User createdUser = userRepository.save(user);
			userAccessToken(user);
			response.setStatus("201");
			response.setUserMessage("User Created with EmailId :: " + createdUser.getEmail());			
		}else {
			response.setStatus("500");
			response.setUserMessage("User Already exists with EmailId :: " + existingUser.getEmail());
		}
		return response;
		
	}
	
	private void userAccessToken(User user) {
		String uniqueID = UUID.randomUUID().toString();
		UserAuth auth = new  UserAuth();
		auth.setuId(user.getId());
		auth.setAccessToken(uniqueID);
		userAuthRepository.save(auth);
		
		
	}
	

}
