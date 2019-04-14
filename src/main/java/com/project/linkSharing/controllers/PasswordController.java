package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import com.project.linkSharing.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;
import java.util.UUID;

@Controller
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    HttpSession session;
    private String token;

    @GetMapping("/forgotPassword")
    public ModelAndView displayForgotPasswordPage() {
        return new ModelAndView("forgotPassword");
    }

    @PostMapping("/forgotPassword")
    public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {
        Optional<User> optional = userService.getByEmail(userEmail);
        if (!optional.isPresent()) {
            modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
        } else {
            User user = optional.get();
            user.setResetToken(UUID.randomUUID().toString());
            token = user.getResetToken();
            userService.saveUpdatedUser(user);
            String appUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + "/resetPassword/" + user.getResetToken());
            emailUtil.sendEmail(passwordResetEmail);
            modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
        }
        modelAndView.setViewName("forgotPassword");
        return modelAndView;
    }

    @GetMapping("/resetPassword/{token}")
    public String display(@PathVariable("token") String tokenString) {
        if(token.equals(tokenString)) {
            return "emailVerification";
        }
        else{
            return "redirect:/login";
        }
    }

    @PostMapping("/resetPassword")
    public String display(HttpServletRequest httpServletRequest) {
            String password = httpServletRequest.getParameter("password");
            String confirmPassword = httpServletRequest.getParameter("confirmPassword");
            if (password.equals(confirmPassword)) {
                Optional<User> optional = userService.getUserByResetToken(token);
                if(optional.isPresent()){
                    User user = optional.get();
                    user.setPassword(password);
                    user.setResetToken(null);
                    userService.saveUpdatedUser(user);
                }
            }
        return "redirect:/login";
    }
}

