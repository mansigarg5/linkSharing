package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    User save(User user);
    User findByUsernameAndPassword(String username, String password);
    User findByEmail(String email);
    User findByFirstName(String firstName);
}
