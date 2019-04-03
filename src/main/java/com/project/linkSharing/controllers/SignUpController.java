package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignUpController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public ModelAndView display(ModelAndView modelAndView){
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView submit(@ModelAttribute User user, HttpServletRequest httpServletRequest){
        if(user.getPassword().equals(httpServletRequest.getParameter("confirmPassword"))){
            userService.saveUser(user);
            return new ModelAndView("dashboard");
        }
        else{
            return new ModelAndView("signup");
        }

    }
}


