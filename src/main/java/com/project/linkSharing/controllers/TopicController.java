package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.response.ResponseSubscription;
import com.project.linkSharing.services.ResourcesService;
import com.project.linkSharing.services.SubscriptionService;
import com.project.linkSharing.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopicController {
    @Autowired
    TopicService topicService;
    @Autowired
    ResourcesService resourcesService;
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/topic/{topicName}")
    public ModelAndView display(@PathVariable("topicName") String topicName){
        List<String> nameList = new ArrayList<>();
        nameList.add(topicName);
        List<Topic> topicList = topicService.findTopicsByName(nameList);
        List<Resources> resourcesList = resourcesService.findResourcesByTopicName(topicList);
        List<Subscription> subscriptionList = subscriptionService.listSubscriptionByTopic(topicList);
        List<ResponseSubscription> responseSubscriptions = subscriptionService.postCountByUserAndTopic(subscriptionList);
        return new ModelAndView("topic").addObject("topicList", topicList)
                .addObject("resourcesList", resourcesList)
                .addObject("responseSubscription", responseSubscriptions);

    }
}
