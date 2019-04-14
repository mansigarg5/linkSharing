package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TopicController {
    @Autowired
    TopicService topicService;

    @GetMapping("/topic/{topicName}")
    public ModelAndView display(@PathVariable("topicName") String topicName){
        Topic topic = topicService.findTopicByName(topicName);
        return new ModelAndView("topic").addObject("topic", topic);

    }
}
