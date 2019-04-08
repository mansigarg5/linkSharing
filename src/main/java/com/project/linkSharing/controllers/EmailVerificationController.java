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
import javax.servlet.http.HttpSession;

@Controller
public class EmailVerificationController {

    @Autowired
    HttpSession session;
    @Autowired
    UserService userService;

    @GetMapping("/emailVerification")
    public String display(){
        return "emailVerification";
    }

    @PostMapping("/resetPassword")
    public String display(HttpServletRequest httpServletRequest){
        String password = httpServletRequest.getParameter("password");
        String confirmPassword = httpServletRequest.getParameter("confirmPassword");
        if(password.equals(confirmPassword)){
            User user = (User)session.getAttribute("user");
            user.setPassword(password);
            userService.saveUser(user);
        }
        return "login";
    }
}
