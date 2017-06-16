package com.retail.repositories;

import org.springframework.data.repository.CrudRepository;

import com.retail.domains.UserAuth;

public interface UserAuthRepository extends CrudRepository<UserAuth, Long> {

}
