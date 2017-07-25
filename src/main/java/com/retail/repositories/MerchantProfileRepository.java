package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.entities.MerchantProfile;

public interface MerchantProfileRepository extends CrudRepository<MerchantProfile, Integer> {

}
