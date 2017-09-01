package com.retail.merchant.repositories;

import com.retail.merchant.entities.MerchantProducts;
import org.springframework.data.repository.CrudRepository;


public interface MechantProductsRepository extends CrudRepository<MerchantProducts, Integer> {
    MerchantProducts findByUserId(Integer userId);
}
