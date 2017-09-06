package com.retail.merchant.services;

import com.retail.merchant.entities.MerchantOrders;
import com.retail.merchant.repositories.MerchantOrdersRepository;
import com.retail.oauth.entities.User;
import com.retail.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    @Autowired
    private MerchantOrdersRepository ordersRepository;

    @Autowired
    private OauthService oauthService;

    public ResponseEntity<MerchantOrders> getOrders(String accessToken) {
        User user = oauthService.checkAccessToken(accessToken);
        if (user != null) {
            MerchantOrders byUserId = ordersRepository.findByUserId(user.getId());
            return new ResponseEntity<MerchantOrders>(byUserId, HttpStatus.OK);
        }else {
            return new ResponseEntity<MerchantOrders>(HttpStatus.UNAUTHORIZED);
        }

    }

    public ResponseEntity<MerchantOrders> saveOrders(String accessToken, MerchantOrders orders) {
        User user = oauthService.checkAccessToken(accessToken);
        if (user != null) {
            orders.setUserId(user.getId());
            MerchantOrders merchantOrders = ordersRepository.save(orders);
            if (merchantOrders != null) {
                return new ResponseEntity<MerchantOrders>(merchantOrders, HttpStatus.OK);
            }else {
                return new ResponseEntity<MerchantOrders>(HttpStatus.NOT_FOUND);
            }

        }else {
            return new ResponseEntity<MerchantOrders>(HttpStatus.UNAUTHORIZED);
        }
    }


}
