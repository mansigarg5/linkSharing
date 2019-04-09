package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ModelAndView display(){
        List<User> userList = userService.userList();
        return new ModelAndView("users").addObject("userList", userList);
    }
}
