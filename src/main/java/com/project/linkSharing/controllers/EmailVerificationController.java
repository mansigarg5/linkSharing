package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmailVerificationController {

    @GetMapping("/emailVerification")
    public ModelAndView display(@ModelAttribute User user){
        return new ModelAndView("emailVerification").addObject("user", user);
    }
}
