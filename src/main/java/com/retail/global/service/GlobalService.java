package com.retail.global.service;

import com.retail.global.entities.Products;
import com.retail.global.entities.ServiceLocations.Locations;
import com.retail.global.repositories.ProductsRepository;
import com.retail.global.repositories.ServiceLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalService {

    @Autowired
    ServiceLocationsRepository serviceLocationsRepository;

    @Autowired
    ProductsRepository productsRepository;

    public List<Locations> getServiceLocations() {
        Iterable<Locations> serviceLocations =  serviceLocationsRepository.findAll();
        List<Locations> locationsList = new ArrayList<>();
        serviceLocations.forEach(locationsList::add);
        return locationsList;
    }

    public List<Locations> postServiceLocations(List<Locations> locations) {
        Iterable<Locations> serviceLocations =  serviceLocationsRepository.save(locations);
        List<Locations> locationsList = new ArrayList<>();
        serviceLocations.forEach(locationsList::add);
        return locationsList;
    }

    public List<Products> getVegitableProduct() {
        Iterable<Products> products = productsRepository.findAll();
        List<Products> productsList = new ArrayList<>();
        products.forEach((productsList:: add));
        return productsList;
    }

}
