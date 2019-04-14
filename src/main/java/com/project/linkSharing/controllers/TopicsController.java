package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.response.ResponseSubscription;
import com.project.linkSharing.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TopicsController {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    HttpSession session;

    @GetMapping("/topics")
    public ModelAndView display(){
        List<Subscription> subscriptionList = subscriptionService.listSubscription();
        List<ResponseSubscription> responseSubscriptions = subscriptionService.postCountByUserAndTopic(subscriptionList);
        return new ModelAndView("topics").addObject("responseSubscription", responseSubscriptions);
    }
}

