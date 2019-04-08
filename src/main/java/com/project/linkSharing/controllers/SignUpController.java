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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.mock.web.MockMultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class SignUpController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    HttpSession session;

    @GetMapping("/signup")
    public ModelAndView display(ModelAndView modelAndView){
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/signup")
    public ModelAndView submit(@ModelAttribute User user, HttpServletRequest httpServletRequest){
        if(user.getPassword().equals(httpServletRequest.getParameter("confirmPassword"))){
            session.setAttribute("user", user);
            List<Topic> topicList = topicService.listTopics();
            userService.saveUser(user);
            return new ModelAndView("dashboard")
                    .addObject("topicList", topicList);
        }
        else{
            return new ModelAndView("signup");
        }

    }
}


