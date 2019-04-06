package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SignUpController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;

    @GetMapping("/signup")
    public ModelAndView display(ModelAndView modelAndView){
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView submit(@ModelAttribute User user, HttpServletRequest httpServletRequest){
        if(user.getPassword().equals(httpServletRequest.getParameter("confirmPassword"))){
            List<Topic> topicList = topicService.listTopics();
            userService.saveUser(user);
            return new ModelAndView("dashboard").addObject("user", user)
                    .addObject("topicList", topicList);
        }
        else{
            return new ModelAndView("signup");
        }

    }
}


