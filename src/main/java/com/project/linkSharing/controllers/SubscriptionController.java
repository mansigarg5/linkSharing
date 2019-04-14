package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.enums.Seriousness;
import com.project.linkSharing.enums.Visibility;
import com.project.linkSharing.services.SubscriptionService;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class SubscriptionController {
    @Autowired
    TopicService topicService;
    @Autowired
    HttpSession session;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    UserService userService;

    @GetMapping("/viewAll")
    public ModelAndView display() {
        List<Topic> topicList = topicService.listTopics();
        return new ModelAndView("viewAll").addObject("topicList", topicList);
    }

    @PostMapping("/unsubscribe")
    @ResponseBody
    public String unsubscribe(@RequestParam("topicId") Integer topicId) {
        User user = (User) session.getAttribute("user");
        Optional<Topic> optionalTopic = topicService.findTopicById(topicId);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            Subscription subscription = subscriptionService.subscriptionByUserAndTopic(user, topic);
            Integer subscriptionId = subscription.getId();
            subscriptionService.deleteSubscription(subscriptionId);
        }
        return "true";
    }

    @PostMapping("/setVisibility")
    @ResponseBody
    public String visibility(@RequestParam("topicId") Integer topicId, @RequestParam("visibility") String visibility) {
        Optional<Topic> optionalTopic = topicService.findTopicById(topicId);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.setVisibility(Visibility.valueOf(visibility));
            topicService.saveTopic(topic);
            return "true";
        }
        else {
            return "false";
        }
    }

    @PostMapping("/setSeriousness")
    @ResponseBody
    public String seriousness(@RequestParam("username") String username, @RequestParam("topicId") Integer topicId,
                              @RequestParam("seriousness") String seriousness) {
        User user = userService.findUserByUsername(username);
        Optional<Topic> optionalTopic = topicService.findTopicByIdAndUser(topicId, user);
        if (optionalTopic.isPresent()) {
            Topic topic = optionalTopic.get();
            topic.setSeriousness(Seriousness.valueOf(seriousness));
            topicService.saveTopic(topic);
            return "true";
        }
        else{
            return "false";
        }
    }

    @PostMapping("/editTopicName")
    @ResponseBody
    public String updateTopic(@RequestParam("oldName") String oldName, @RequestParam("newName") String newName){
        Topic topic = topicService.findTopicByName(oldName);
        topic.setName(newName);
        topicService.saveTopic(topic);
        return "true";
    }

    @PostMapping("/deleteTopic")
    @ResponseBody
    public String delete(@RequestParam("topicId") Integer topicId){
        topicService.deleteTopic(topicId);
        return "true";
    }
}
