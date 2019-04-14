package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.ResourcesService;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/")
    public String display1() {
//        resourcesService.countMaxResourcesByTopic();
        if (session.getAttribute("user") != null) {
            return "redirect:/dashboard";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView display() {
        List<Topic> publicTopicList = topicService.listPublicTopic();
        List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicList);
        return new ModelAndView("login").addObject("resourcesList", resourcesList);
    }

    @PostMapping("/login")
    public String submit(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        Optional<User> optional = userService.getUserByUsernameAndPassword(username, password);
        if (optional.isPresent()) {
            User user = optional.get();
            session.setAttribute("user", user);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return "redirect:/dashboard";
            }
            else {
                return "redirect:/login";
            }
        }
        else {
            return "redirect:/login";
        }
    }

    @PostMapping("/search")
    public ModelAndView searchTopic(HttpServletRequest httpServletRequest){
        String name = httpServletRequest.getParameter("search");
        System.out.println("name is :" + name);
        List<Topic> topicList = topicService.findAllTopicByName(name);
        if(topicList.get(0)==null){
            List<Topic> publicTopicList = topicService.listPublicTopic();
            List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicList);
            return new ModelAndView("login").addObject("resourcesList", resourcesList);
        }
        else{
            List<Resources> resourcesList = resourcesService.findResourcesByTopicName(topicList);
            return new ModelAndView("search").addObject("topicList", topicList)
                    .addObject("resourcesList", resourcesList);
        }
    }
}


