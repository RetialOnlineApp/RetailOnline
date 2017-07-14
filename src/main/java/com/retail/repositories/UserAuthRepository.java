package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.entities.UserAuth;

public interface UserAuthRepository extends CrudRepository<UserAuth, Integer> {
	UserAuth findByVerifyToken(String token);

	UserAuth findByEmail(String email);

	UserAuth findByEmailInAndPasswordIn(String email, String password);

}
