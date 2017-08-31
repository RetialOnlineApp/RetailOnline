package com.retail.merchant.repositories;

import com.retail.merchant.entities.MerchantProfile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantProfileRepository extends CrudRepository<MerchantProfile, Integer> {
	public MerchantProfile findByUserId(Integer userId);

}
