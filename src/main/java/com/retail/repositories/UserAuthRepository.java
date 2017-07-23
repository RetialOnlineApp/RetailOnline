package com.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.retail.entities.UserAuth;
import com.retail.entities.UserProfile;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
	UserAuth findByVerifyToken(String token);

	UserAuth findByEmail(String email);

	UserAuth findByEmailInAndPasswordIn(String email, String password);
	
	@Query("SELECT p FROM user_auth p WHERE LOWER(p.access_token) = LOWER(:access_token)")
	UserAuth findByAccessToken(@Param("access_token") String accessToken);

	UserProfile save(UserProfile profile);

}
