package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.entities.MarchantAuth;
import com.retail.entities.UserAuth;

public interface MarchantAuthRepository extends CrudRepository<MarchantAuth, Integer> {
	MarchantAuth findByEmail(String email);
	MarchantAuth findByVerifyToken(String token);
	MarchantAuth findByEmailInAndPasswordIn(String email, String password);


}
