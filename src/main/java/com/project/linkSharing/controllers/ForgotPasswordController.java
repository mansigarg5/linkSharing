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
public class ForgotPasswordController {

    @Autowired
    UserService userService;

    @GetMapping("/forgotPassword")
    public String display(){
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public ModelAndView submit(HttpServletRequest httpServletRequest){
        String email = httpServletRequest.getParameter("email");
        User user = userService.getUserByEmail(email);
        return new ModelAndView("emailVerification").addObject("user",user);

    }
}
