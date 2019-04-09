package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EditProfileController {

    @Autowired
    HttpSession session;
    @Autowired
    UserService userService;

    @GetMapping("/editProfile")
    public String display(){
        return "editProfile";
    }

    @PostMapping("/changeProfile")
    public String displayProfile(HttpServletRequest httpServletRequest){
        User user = (User)session.getAttribute("user");
        user.setFirstName(httpServletRequest.getParameter("firstName"));
        user.setLastName(httpServletRequest.getParameter("lastName"));
        user.setUsername(httpServletRequest.getParameter("username"));
        userService.saveUser(user);
        return "editProfile";
    }

    @PostMapping("/changePassword")
    public String display(HttpServletRequest httpServletRequest){
        String password = httpServletRequest.getParameter("password");
        String confirmPassword = httpServletRequest.getParameter("confirmPassword");
        if(password.equals(confirmPassword)){
            User user = (User)session.getAttribute("user");
            user.setPassword(password);
            userService.saveUser(user);
            return "editProfile";
        }
        else{
            return "login";
        }
    }
}
