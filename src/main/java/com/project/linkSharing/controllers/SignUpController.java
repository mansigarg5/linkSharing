package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignUpController {

    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @GetMapping("/signup")
    public ModelAndView display(){
        return new ModelAndView("signup").addObject("user", new User());
    }

    @PostMapping("/signup")
    public String submit(@ModelAttribute User user, HttpServletRequest httpServletRequest){
        if(user.getPassword().equals(httpServletRequest.getParameter("confirmPassword"))){
            session.setAttribute("user", user);
            user.setActivate("activate");
            userService.saveUser(user);
            return "redirect:/dashboard";
        }
        else{
            return "redirect:/signup";
        }
    }
}


