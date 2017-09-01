package com.retail.merchant.controllers;

import com.retail.merchant.entities.MerchantProducts;
import com.retail.merchant.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
public class MerchantProductController {

    @Autowired
    ProductService service;

    @PostMapping("/products/add")
    public ResponseEntity<MerchantProducts> addProduct(@RequestBody MerchantProducts merchantProducts,
                                                      @RequestHeader String accessToken) {
        return service.saveProducts(merchantProducts, accessToken);
    }

    @GetMapping("/products/get")
    public ResponseEntity<MerchantProducts> getProduct(@RequestHeader String accessToken) {
        return service.getSeletedProducts(accessToken);
    }

}
