package com.retail.global.controllers;


import com.retail.global.entities.Product;
import com.retail.global.entities.ServiceLocations.Locations;
import com.retail.global.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/service")
public class GlobalServiceController {

    @Autowired
    GlobalService service;

    @GetMapping("/locations")
    public ResponseEntity<List<Locations>> getServiceLocations() {
        List<Locations> locations = service.getServiceLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @PostMapping("/locations")
    public ResponseEntity<List<Locations>> postServiceLocations(@RequestBody List<Locations> locations) {
        List<Locations> foundLocations = service.postServiceLocations(locations);
        return new ResponseEntity<>(foundLocations, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getVegitableProducts() {
        List<Product> productList = service.getVegitableProduct();
        return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
    }
}
