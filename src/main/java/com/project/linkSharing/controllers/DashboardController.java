package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.EmailService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
        User user = (User)session.getAttribute("user");
        String name = httpServletRequest.getParameter("name");
        String visibility = httpServletRequest.getParameter("visibility");
        Topic topic = new Topic();
        topic.setName(name);
        topic.setVisibility(visibility);
        topic.setSeriousness("Very serious");
        topic.setCreatedBy(user.getUsername());
        topic.setDate(new Date());
        topicService.saveTopic(topic);
        return "dashboard";
    }

    @PostMapping("/saveLink")
    public String saveLink(HttpServletRequest httpServletRequest){
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
        return "dashboard";
    }

    @PostMapping("/saveDocument")
    public String saveDocument(HttpServletRequest httpServletRequest){
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
        return "dashboard";
    }

    @PostMapping("/sendInvite")
    public String sendEmailInvite(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) throws MessagingException {
        String name = httpServletRequest.getParameter("name");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        emailService.sendEmail(name, redirectAttributes);
        return "dashboard";
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


