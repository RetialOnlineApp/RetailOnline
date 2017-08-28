package com.retail.merchant.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.merchant.entities.MerchantProfile;

public interface MerchantProfileRepository extends CrudRepository<MerchantProfile, Integer> {
	public MerchantProfile findByMerchantId(Integer merchantId);

}
