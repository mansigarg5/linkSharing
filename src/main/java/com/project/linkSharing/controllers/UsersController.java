package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ModelAndView display(){
        List<User> userList = userService.userList();
        return new ModelAndView("users").addObject("userList", userList);
    }

    @PostMapping("/activateUser")
    @ResponseBody
    public String activateUser(@RequestParam("userId") Integer userId, @RequestParam("activate") String activate){
        Optional<User> optionalUser = userService.findUserById(userId);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setActivate(activate);
            userService.saveUpdatedUser(user);
        }
        return "true";
    }
}
