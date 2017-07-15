package com.retail.repositories;


import org.springframework.data.repository.CrudRepository;

import com.retail.entities.MarchantProfile;

public interface MarchantProfileRepository extends CrudRepository<MarchantProfile, Integer> {

}
