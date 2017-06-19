package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.entities.Marchant;
import com.retail.entities.MarchantAuth;

public interface MarchantAuthRepository extends CrudRepository<MarchantAuth, Long> {

}
