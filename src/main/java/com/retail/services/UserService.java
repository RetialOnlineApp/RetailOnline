package com.retail.services;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.retail.domains.Response;
import com.retail.entities.User;
import com.retail.entities.UserAuth;
import com.retail.repositories.UserAuthRepository;
import com.retail.repositories.UserRepository;
import com.retail.util.SignUpMailer;

@Service
public class UserService {
	
	
	/*UserRepository userRepository;
    UserAuthRepository userAuthRepository;
	
	
	public UserService(UserRepository userRepository,
    UserAuthRepository userAuthRepository) {
		this.userRepository = userRepository;
		this.userAuthRepository = userAuthRepository;
	}*/
	
	public Response userSignUp(User user, UserRepository userRepository,
    UserAuthRepository userAuthRepository) {
		Response response = new Response();
		User existingUser = userRepository.findByEmail(user.getEmail());
		if (existingUser == null) {
			User createdUser = userRepository.save(user);
			userAccessToken(user, userAuthRepository);
			response.setStatus("201");
			response.setUserMessage("User Created with EmailId :: " + createdUser.getEmail());			
		}else {
			response.setStatus("500");
			response.setUserMessage("User Already exists with EmailId :: " + existingUser.getEmail());
		}
		return response;
		
	}
	
	
	public Response verifyUser(String random, UserAuthRepository authRepository) {
		Response response = new Response();
		UserAuth auth = authRepository.findByRandomId(random);
		if (auth != null) {
			auth.setVerified(true);
			response.setUserMessage("Account verified.. Thanks for you time");
			response.setStatus("200");
		} else {
			response.setStatus("401");
			response.setUserMessage("Sorry ! Bad try..");
		}
		
		return response;
	}
	
	private void userAccessToken(User user ,  UserAuthRepository userAuthRepository) {
		String accessToken = UUID.randomUUID().toString();
		String random = UUID.randomUUID().toString();
		UserAuth auth = new  UserAuth();
		auth.setuId(user.getId());
		auth.setAccessToken(accessToken);
		auth.setRandomId(random);
		auth.setVerified(false);
		sendVerificationMail(user,random);
		userAuthRepository.save(auth);
		
	}
	private void sendVerificationMail(User user,String random ) {
		SignUpMailer mailer = new SignUpMailer(); 
		boolean status = mailer.send(user.getEmail(), random);
	}
	
	

}
