package com.retail.merchant.services;


import com.retail.merchant.entities.MerchantProducts;
import com.retail.merchant.repositories.MechantProductsRepository;
import com.retail.oauth.entities.User;
import com.retail.oauth.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    MechantProductsRepository mechantProductsRepository;

    @Autowired
    OauthService oauthService;



    public ResponseEntity<MerchantProducts> getSeletedProducts(String accessToken) {
        User user = oauthService.checkAccessToken(accessToken);
        if (user != null) {
            MerchantProducts byUserId = mechantProductsRepository.findByUserId(user.getId());
            if (byUserId != null) {
                return new ResponseEntity<>(byUserId, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<MerchantProducts> saveProducts(MerchantProducts merchantProducts, String accessToken) {
        User user = oauthService.checkAccessToken(accessToken);
        if (user != null) {
            merchantProducts.setUserId(user.getId());
            MerchantProducts save = mechantProductsRepository.save(merchantProducts);
            return new ResponseEntity<>(save, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }



}
