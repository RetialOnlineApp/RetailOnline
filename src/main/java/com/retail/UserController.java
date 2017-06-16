package com.retail;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.domains.User;
import com.retail.domains.UserAuth;
import com.retail.repositories.UserAuthRepository;
import com.retail.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
    UserAuthRepository userAuthRepository;
	
	
	@PostMapping("/signup")
	public User addUser(@RequestBody User user) {
		User resUser = userRepository.save(user);
		accessToken(user);
		return resUser;
		
	}
	
	private void accessToken(User user) {
		String uniqueID = UUID.randomUUID().toString();
		UserAuth auth = new  UserAuth();
		auth.setuId(user.getId());
		auth.setAccessToken(uniqueID);
		userAuthRepository.save(auth);
		
		
	}

}
