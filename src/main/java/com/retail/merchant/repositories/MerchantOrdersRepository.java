package com.retail.merchant.repositories;

import com.retail.merchant.entities.MerchantOrders;
import org.springframework.data.repository.CrudRepository;


public interface MerchantOrdersRepository extends CrudRepository<MerchantOrders, Integer> {

    MerchantOrders findByUserId(Integer userId);
}
