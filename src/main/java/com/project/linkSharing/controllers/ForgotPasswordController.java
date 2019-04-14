package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.services.EmailService;
import com.project.linkSharing.services.ResourcesService;
import com.project.linkSharing.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ForgotPasswordController {

    @Autowired
    EmailService emailService;
    @Autowired
    TopicService topicService;
    @Autowired
    ResourcesService resourcesService;

   /* @GetMapping("/forgotPassword")
    public String display() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    @ResponseBody
    public String processForgotPasswordForm(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) throws MessagingException {
        String email1 = httpServletRequest.getParameter("email");
        String mail = emailService.sendEmail(email1, redirectAttributes, httpServletRequest);
        if (mail.equals("login")) {
            List<String> publicTopicNameList = topicService.listPublicTopicName();
            List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicNameList);
            return "login";
        }
        else{
            return "emailVerification";
        }
    }*/
}

