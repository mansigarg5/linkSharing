package com.project.linkSharing.services;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    public User saveUser(User user){
       return userRepository.save(user);
    }
    public User getUserByUsernameAndPassword(String username, String password){
        return userRepository.getUserByUsernameAndPassword(username, password);
    }

    public User getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

}
