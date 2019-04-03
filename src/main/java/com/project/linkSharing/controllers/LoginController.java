package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String display(){
        return "login";
    }

    @PostMapping("/")
    public ModelAndView submit(HttpServletRequest httpServletRequest){
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        User user1 = userService.getUserByUsernameAndPassword(username, password);
        if(user1.getUsername().equals(username) && user1.getPassword().equals(password)){
            return new ModelAndView("dashboard").addObject("user", user1);
        }
        else {
            return new ModelAndView("login");
        }
    }
}


