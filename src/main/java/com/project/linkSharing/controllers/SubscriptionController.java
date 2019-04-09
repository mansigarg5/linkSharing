package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    HttpSession session;
    @PostMapping("/subscriptionList")
    public ModelAndView display(){
        User user = (User)session.getAttribute("user");
        List<Subscription> subscriptionList = subscriptionService.listSubscriptionByUserid(user.getId());
        return new ModelAndView("subscription").addObject("subscriptionList", subscriptionList);
    }
}
