package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;

    @GetMapping("/dashboard")
    public String display(){
        return "dashboard";
    }

//    @PostMapping("/search")
//    public String search(HttpServletRequest httpServletRequest){
//        String name = httpServletRequest.getParameter("search");
//        userService.getUserByName(name);
//        return "user";
//    }
//
//    @PostMapping("/createTopic")
//    public void createTopic(@ModelAttribute Topic topic){
//        topicService.saveTopic(topic);
//    }
}
