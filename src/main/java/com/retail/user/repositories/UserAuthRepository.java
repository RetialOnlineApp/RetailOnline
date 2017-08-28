package com.retail.user.repositories;

import com.retail.user.entities.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuth, Integer> {
	UserAuth findByVerifyToken(String token);

	UserAuth findByEmail(String email);

	UserAuth findByEmailInAndPasswordIn(String email, String password);


}
