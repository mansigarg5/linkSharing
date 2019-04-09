package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    ResourcesService resourcesService;
    @Autowired
    EmailService emailService;
    @Autowired
    HttpSession session;
    @Autowired
    SubscriptionService subscriptionService;



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
    public  ModelAndView saveTopic(HttpServletRequest httpServletRequest) {
        User user = (User) session.getAttribute("user");
        String name = httpServletRequest.getParameter("name");
        String visibility = httpServletRequest.getParameter("visibility");
        Topic topic = new Topic();
        topic.setName(name);
        topic.setVisibility(visibility);
        topic.setCreatedBy(user.getUsername());
        topic.setDate(new Date());
        topicService.saveTopic(topic);
        Subscription subscription = new Subscription();
        subscription.setTopicid(topic.getId());
        subscription.setUserid(user.getId());
        subscription.setSeriousness("Very Serious");
        subscription.setVisibility(visibility);
        subscriptionService.saveSubscription(subscription);
        List<Topic> topicList = topicService.listTopics();
        List<Subscription> subscriptionList = subscriptionService.listSubscriptionByUserid(user.getId());
        Integer subscriptionCount = subscriptionService.countSubscription(user.getId());
        Integer postCount = topicService.countTopicByCreatedBy(user.getUsername());
        return new ModelAndView("dashboard").addObject("topicList", topicList)
                .addObject("subscriptionList", subscriptionList)
                .addObject("subscriptionCount", subscriptionCount)
                .addObject("postCount", postCount);
    }


//    @PostMapping("/saveTopic")
//    public ModelAndView saveTopic(@RequestParam Map<String, String> map){
//        User user = (User)session.getAttribute("user");
//        String name = map.get("name");
//        System.out.println(name);
//        String visibility = map.get("visibility");
//        System.out.println(visibility);
//        Topic topic = new Topic();
//        topic.setName(name);
//        topic.setVisibility(visibility);
//        topic.setSeriousness("Very serious");
//        topic.setCreatedBy(user.getUsername());
//        topic.setDate(new Date());
//        topicService.saveTopic(topic);
//        List<Topic> topicList = topicService.listTopics();
//        return new ModelAndView("dashboard").addObject("topicList", topicList);
//    }

    @PostMapping("/saveLink")
    public ModelAndView saveLink(HttpServletRequest httpServletRequest){
        User user = (User)session.getAttribute("user");
        String link = httpServletRequest.getParameter("link");
        String description = httpServletRequest.getParameter("description");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        Resources resources = new Resources();
        resources.setLink(link);
        resources.setDescription(description);
        resources.setRelatedTopic(relatedTopic);
        resources.setCreatedBy(user.getUsername());
        resources.setDate(new Date());
        resourcesService.saveResources(resources);
        List<Topic> topicList = topicService.listTopics();
        List<Subscription> subscriptionList = subscriptionService.listSubscriptionByUserid(user.getId());
        Integer postCount = topicService.countTopicByCreatedBy(user.getUsername());
        Integer subscriptionCount = subscriptionService.countSubscription(user.getId());
        return new ModelAndView("dashboard")
                .addObject("topicList", topicList)
                .addObject("subscriptionList", subscriptionList)
                .addObject("subscriptionCount", subscriptionCount)
                .addObject("postCount", postCount);
    }

    @PostMapping("/saveDocument")
    public ModelAndView saveDocument(HttpServletRequest httpServletRequest){
        User user = (User)session.getAttribute("user");
        String document = httpServletRequest.getParameter("document");
        String description = httpServletRequest.getParameter("description");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        Resources resources = new Resources();
        resources.setDocument(document);
        resources.setDescription(description);
        resources.setRelatedTopic(relatedTopic);
        resources.setCreatedBy(user.getUsername());
        resources.setDate(new Date());
        resourcesService.saveResources(resources);
        List<Topic> topicList = topicService.listTopics();
        List<Subscription> subscriptionList = subscriptionService.listSubscriptionByUserid(user.getId());
        Integer postCount = topicService.countTopicByCreatedBy(user.getUsername());
        Integer subscriptionCount = subscriptionService.countSubscription(user.getId());
        return new ModelAndView("dashboard")
                .addObject("topicList", topicList)
                .addObject("subscriptionList", subscriptionList)
                .addObject("subscriptionCount", subscriptionCount)
                .addObject("postCount", postCount);
    }

    @PostMapping("/sendInvite")
    public ModelAndView sendEmailInvite(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) throws MessagingException {
        String email = httpServletRequest.getParameter("email");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        User user = (User)session.getAttribute("user");
        System.out.println("1");
        emailService.sendInvite(email, relatedTopic, redirectAttributes);
        System.out.println("hii");
        List<Topic> topicList = topicService.listTopics();
        List<Subscription> subscriptionList = subscriptionService.listSubscriptionByUserid(user.getId());
        Integer postCount = topicService.countTopicByCreatedBy(user.getUsername());
        Integer subscriptionCount = subscriptionService.countSubscription(user.getId());
        return new ModelAndView("dashboard")
                .addObject("topicList", topicList)
                .addObject("subscriptionList", subscriptionList)
                .addObject("subscriptionCount", subscriptionCount)
                .addObject("postCount", postCount);
    }

    @GetMapping("/logout")
    public ModelAndView logoutUser(){
        session.removeAttribute("user");
        session.invalidate();
        List<String> publicTopicNameList = topicService.listPublicTopicName();
        List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicNameList);
        return new ModelAndView("login").addObject("resourcesList", resourcesList);
    }




}


