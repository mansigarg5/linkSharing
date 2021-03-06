package com.project.linkSharing.services;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    private static final String dirpath = "/home/ttn/linkSharing/src/main/resources/static/images";

    public User saveUser(User user) {
        userRepository.save(user);
        Path path = Paths.get(dirpath, user.getId() + ".jpeg");
        try {
            path = Files.write(path, user.getImage().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setFileName(path.toString());
        return userRepository.save(user);
    }

    public User saveUpdatedUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsernameAndPassword(String username, String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserByResetToken(String resetToken){
        return userRepository.findByResetToken(resetToken);
    }

    public User getUserByName(String name){
        return userRepository.findByFirstName(name);
    }

    public List<User> userList(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Integer userId){
        return userRepository.findById(userId);
    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
