package com.retail.oauth.repositories;

import com.retail.oauth.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends CrudRepository<User, Integer> {

    User findByEmail(String email);

    User findByVerifyToken(String token);

    User findByEmailInAndPasswordIn(String email, String password);

    User findByAccessToken(String accessToken);
}
