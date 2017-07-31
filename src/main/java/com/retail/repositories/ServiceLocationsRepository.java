package com.retail.repositories;


import com.retail.entities.ServiceLocations.Locations;
import org.springframework.data.repository.CrudRepository;

public interface ServiceLocationsRepository extends CrudRepository<Locations, Integer>{
}
