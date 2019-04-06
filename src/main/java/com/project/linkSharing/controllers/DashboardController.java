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
        return "login";
    }

//    @PostMapping("/search")
//    public String search(HttpServletRequest httpServletRequest){
//        String name = httpServletRequest.getParameter("search");
//        userService.getUserByName(name);
//        return "user";
//    }


    @PostMapping("/saveTopic")
    public String saveTopic(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("name");
        String visibility = httpServletRequest.getParameter("visibility");
        Topic topic = new Topic();
        topic.setName(name);
        topic.setVisibility(visibility);
        topicService.saveTopic(topic);
        return "dashboard";
    }
    @PostMapping("/saveLink")
    public String saveLink(HttpServletRequest httpServletRequest){
        String link = httpServletRequest.getParameter("link");
        String description = httpServletRequest.getParameter("description");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        Resources resources = new Resources();
        resources.setLink(link);
        resources.setDescription(description);
        resources.setRelatedTopic(relatedTopic);
        resourcesService.saveResources(resources);
        return "dashboard";
    }

    @PostMapping("/saveDocument")
    public String saveDocument(HttpServletRequest httpServletRequest){
        String document = httpServletRequest.getParameter("document");
        String description = httpServletRequest.getParameter("description");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        Resources resources = new Resources();
        resources.setDocument(document);
        resources.setDescription(description);
        resources.setRelatedTopic(relatedTopic);
        resourcesService.saveResources(resources);
        return "dashboard";
    }

    @PostMapping("/saveResources")
    public String saveResources(@ModelAttribute Resources resources){
        resourcesService.saveResources(resources);
        return "dashboard";
    }



}


