package com.project.linkSharing.controllers;


import com.project.linkSharing.emailSending.Email;
import com.project.linkSharing.emailSending.EmailService;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ForgotPasswordController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;

    @GetMapping("/forgotPassword")
    public String display(){
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public ModelAndView submit(HttpServletRequest httpServletRequest) throws IOException, MessagingException {
        String email1 = httpServletRequest.getParameter("email");
        User user = userService.getUserByEmail(email1);
        emailService.sendmail(user);
        return new ModelAndView("emailVerification");

    }


}
