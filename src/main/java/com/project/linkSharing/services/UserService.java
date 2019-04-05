package com.project.linkSharing.services;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User saveUser(User user){
       return userRepository.save(user);
    }
    public User getUserByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getUserByName(String name){
        return userRepository.findByFirstName(name);
    }

}
