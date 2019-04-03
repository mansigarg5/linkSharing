package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    User save(User user);
    User getUserByUsernameAndPassword(String username, String password);
    User getUserByEmail(String email);
}
