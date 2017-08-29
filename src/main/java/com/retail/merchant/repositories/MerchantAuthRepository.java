package com.retail.merchant.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.merchant.entities.MerchantAuth;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantAuthRepository extends CrudRepository<MerchantAuth, Integer> {
	MerchantAuth findByEmail(String email);

	MerchantAuth findByVerifyToken(String token);

	MerchantAuth findByEmailInAndPasswordIn(String email, String password);
	
	MerchantAuth findByAccessToken(String accessToken);


}
