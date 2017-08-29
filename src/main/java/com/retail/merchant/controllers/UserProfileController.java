package com.retail.merchant.controllers;

import com.retail.merchant.entities.MerchantProfile;
import com.retail.merchant.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class UserProfileController {

    @Autowired
    MerchantService  service;

    @PostMapping("/profile")
    public ResponseEntity<MerchantProfile> createProfile(@RequestBody MerchantProfile profile, @RequestHeader String accessToken) {
        return service.saveProfile(profile, accessToken);
    }

    @GetMapping("/profile")
    public ResponseEntity<MerchantProfile> getProfile(@RequestHeader String accessToken) {
        return service.getProfile(accessToken);
    }

}
