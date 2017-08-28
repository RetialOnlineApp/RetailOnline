package com.retail.merchant.controllers;

import com.retail.merchant.entities.MerchantProfile;
import com.retail.merchant.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class UserProfileController {

    @Autowired
    MerchantService  service;

    @PostMapping("/profile")
    public ResponseEntity<MerchantProfile> createProfile(@RequestBody MerchantProfile profile, @RequestHeader String accessToken) {
        MerchantProfile savedProfile = service.saveProfile(profile, accessToken);
        if (savedProfile != null) {
            return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(savedProfile, HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/profile")
    public ResponseEntity<MerchantProfile> getProfile(@RequestHeader String accessToken) {
        MerchantProfile profile = service.getProfile(accessToken);
        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(profile, HttpStatus.UNAUTHORIZED);
        }

    }

}
