package com.retail.merchant.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.retail.merchant.domains.Response;
import com.retail.merchant.entities.MerchantProfile;
import com.retail.merchant.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class MerchantProfileController {

    @Autowired
    ProfileService service;

    @PostMapping("/profile")
    public ResponseEntity<MerchantProfile> createProfile(@RequestBody MerchantProfile profile, @RequestHeader String accessToken) {
        return service.saveProfile(profile, accessToken);
    }

    @GetMapping("/profile")
    public ResponseEntity<MerchantProfile> getProfile(@RequestHeader String accessToken) {
        return service.getProfile(accessToken);
    }

    @PostMapping("/profile/updatePassword")
    public ResponseEntity<Response> updatePassword(@RequestBody JsonNode updatePassword,
                                                   @RequestHeader String accessToken) {
       return service.updatePassword(accessToken, updatePassword);
    }

}
