package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.domains.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
