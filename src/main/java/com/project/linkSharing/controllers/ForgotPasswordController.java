package com.project.linkSharing.controllers;

import com.project.linkSharing.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ForgotPasswordController {

    @Autowired
    EmailService emailService;

    @GetMapping("/forgotPassword")
    public String display() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String submit(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) throws MessagingException {
        String email1 = httpServletRequest.getParameter("email");
        String mail = emailService.sendEmail(email1, redirectAttributes);
        if (mail.equals("login")) {
            return "login";
        }
        else{
            return "emailVerification";
        }
    }
}

