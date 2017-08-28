package com.retail.user.repositories;

import com.retail.user.entities.UserProfile;
import org.springframework.data.repository.CrudRepository;


public interface UserProfileRepository extends CrudRepository<UserProfile, Integer> {

}
