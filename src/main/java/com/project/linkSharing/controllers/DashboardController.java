package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.ResourcesService;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DashboardController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    ResourcesService resourcesService;

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

    @GetMapping("/createTopic")
    public void createTopic(Model model){
        model.addAttribute("topic", new Topic());
    }

    @PostMapping("/saveTopic")
    public void saveTopic(@ModelAttribute Topic topic){
        topicService.saveTopic(topic);
    }

    @GetMapping("/createResources")
    public void createResources(Model model){
        model.addAttribute("resources", new Resources());
    }

    @PostMapping("/createResources")
    public void saveResources(@ModelAttribute Resources resources){
        resourcesService.saveResources(resources);
    }



}


