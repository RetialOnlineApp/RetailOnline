package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.entities.Marchant;

public interface MarchantRepository extends CrudRepository<Marchant, Long> {
	Marchant findByEmail(String email);
}
