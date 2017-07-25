package com.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.retail.entities.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
	UserAuth findByVerifyToken(String token);

	UserAuth findByEmail(String email);

	UserAuth findByEmailInAndPasswordIn(String email, String password);


}
