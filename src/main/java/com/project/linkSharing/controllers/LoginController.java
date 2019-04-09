package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.ResourcesService;
import com.project.linkSharing.services.SubscriptionService;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    ResourcesService resourcesService;
    @Autowired
    HttpSession session;
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/")
    public ModelAndView display1(){
        List<String> publicTopicNameList = topicService.listPublicTopicName();
        List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicNameList);
        return new ModelAndView("login").addObject("resourcesList", resourcesList);
    }
    @GetMapping("/login")
    public ModelAndView display(){
        List<String> publicTopicNameList = topicService.listPublicTopicName();
        List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicNameList);
        return new ModelAndView("login").addObject("resourcesList", resourcesList);
    }

    @PostMapping("/login")
    public ModelAndView submit(HttpServletRequest httpServletRequest){
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        User user = userService.getUserByUsernameAndPassword(username, password);
        session.setAttribute("user", user);
        if(user.getUsername().equals(username) && user.getPassword().equals(password)){
            List<Topic> topicList = topicService.listTopics();
            List<Subscription> subscriptionList = subscriptionService.listSubscriptionByUserid(user.getId());
            Integer postCount = topicService.countTopicByCreatedBy(user.getUsername());
            Integer subscriptionCount = subscriptionService.countSubscription(user.getId());
            return new ModelAndView("dashboard")
                    .addObject("topicList", topicList)
                    .addObject("subscriptionList", subscriptionList)
                    .addObject("subscriptionCount", subscriptionCount)
                    .addObject("postCount", postCount);
//                    .addObject("image", "/images/"+ user1.getFirstName() + ".jpeg");
        }
        else {
            List<String> publicTopicNameList = topicService.listPublicTopicName();
            List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicNameList);
            return new ModelAndView("login").addObject("resourcesList", resourcesList);
        }
    }
}


