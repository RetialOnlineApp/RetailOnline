package com.retail.merchant.repositories;


import com.retail.global.entities.ServiceLocations.Locations;
import org.springframework.data.repository.CrudRepository;

public interface ServiceLocationsRepository extends CrudRepository<Locations, Integer>{
}
