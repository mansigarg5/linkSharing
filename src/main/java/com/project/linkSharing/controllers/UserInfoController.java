package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.SubscriptionService;
import com.project.linkSharing.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserInfoController {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    HttpSession session;
    @Autowired
    TopicService topicService;

    @GetMapping("/subscriptionCount")
    public ModelAndView display(){
        User user = (User)session.getAttribute("user");
        List<Topic> topicList = topicService.listTopics();
        List<Subscription> subscriptionList = subscriptionService.subscriptionListByUser(user, topicList);
        return new ModelAndView("subscription").addObject("subscriptionList", subscriptionList);
    }

    @GetMapping("/topicCount")
    public ModelAndView displayTopicCount(){
        User user = (User)session.getAttribute("user");
        List<Topic> topicList = topicService.topicCreatedByUser(user);
        return new ModelAndView("topicCreatedByUser").addObject("topicList", topicList);
    }
}

