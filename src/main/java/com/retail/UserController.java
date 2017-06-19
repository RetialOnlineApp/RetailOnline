package com.retail;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.domains.Response;
import com.retail.entities.User;
import com.retail.repositories.UserAuthRepository;
import com.retail.repositories.UserRepository;
import com.retail.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
    UserAuthRepository userAuthRepository;
	
	
	UserService service =  new UserService();
	
	@PostMapping("/signup")
	public Response addUser(@RequestBody User user) {
		return service.userSignUp(user, userRepository, userAuthRepository);
		}
	
	

}
