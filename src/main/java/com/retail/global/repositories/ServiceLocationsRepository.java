package com.retail.global.repositories;


import com.retail.global.entities.ServiceLocations.Locations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ServiceLocationsRepository extends CrudRepository<Locations, Integer>{
}
