package com.retail.merchant.controllers;


import com.retail.merchant.entities.MerchantOrders;
import com.retail.merchant.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public class MerchantOrdersController {

    @Autowired
    OrdersService service;

    @PostMapping("/orders")
    public ResponseEntity<MerchantOrders> addProduct(@RequestBody MerchantOrders orders,
                                                     @RequestHeader String accessToken) {
        return service.saveOrders(accessToken, orders);
    }

    @GetMapping("/orders")
    public ResponseEntity<MerchantOrders> getProduct(@RequestHeader String accessToken) {
        return service.getOrders(accessToken);
    }
}
