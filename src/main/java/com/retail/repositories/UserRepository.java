package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.entities.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	User findByEmail(String email);

}
