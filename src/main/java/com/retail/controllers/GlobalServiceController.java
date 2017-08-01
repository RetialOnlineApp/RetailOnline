package com.retail.controllers;


import com.retail.entities.ServiceLocations.Locations;
import com.retail.services.GlobalService;
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
}
