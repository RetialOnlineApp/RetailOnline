package com.oauth.repositories;

import com.oauth.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findByVerifyToken(String token);

    User findByEmailInAndPasswordIn(String email, String password);

    User findByAccessToken(String accessToken);
}
