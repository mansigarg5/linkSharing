package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;

    @GetMapping("/")
    public String display1(){
        return "login";
    }
    @GetMapping("/login")
    public String display(){
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView submit(HttpServletRequest httpServletRequest){
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        User user1 = userService.getUserByUsernameAndPassword(username, password);
        if(user1.getUsername().equals(username) && user1.getPassword().equals(password)){
            List<Topic> topicList = topicService.listTopics();
            return new ModelAndView("dashboard")
                    .addObject("user", user1).addObject("topicList", topicList);
//                    .addObject("image", "/images/"+ user1.getFirstName() + ".jpeg");
        }
        else {
            return new ModelAndView("login");
        }
    }
}


