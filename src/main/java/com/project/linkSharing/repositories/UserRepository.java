package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {
    User save(User user);
    Optional<User> findByUsernameAndPassword(String username, String password);
    User findUserByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByResetToken(String resetToken);
    User findByFirstName(String firstName);
    List<User> findAll();
    Optional<User> findById(Integer id);
    User findByUsername(String username);
}
