package com.retail.global.controllers;


import com.retail.global.entities.Products;
import com.retail.global.entities.ServiceLocations.Locations;
import com.retail.global.service.GlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    public ResponseEntity<List<Products>> getVegitableProducts() {
        List<Products> productsList = service.getVegitableProduct();
        return new ResponseEntity<List<Products>>(productsList, HttpStatus.OK);
    }


    @RequestMapping(value = "/product", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getProductImage(@RequestParam String pid) {

        ClassPathResource imgFile = new ClassPathResource("products/"+pid+".jpg");
        byte[] bytes = new byte[0];
        try {
            bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound();
        }



    }

}
