package com.retail.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.retail.entities.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, String> {

}
