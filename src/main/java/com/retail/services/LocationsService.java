package com.retail.services;

import com.retail.entities.ServiceLocations.Locations;
import com.retail.repositories.ServiceLocationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {

    @Autowired
    ServiceLocationsRepository serviceLocationsRepository;

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

}
