package com.project.linkSharing.services;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import com.project.linkSharing.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Component
public class EmailService {

    @Autowired
    UserService userService;
    @Autowired
    EmailUtil emailUtil;
    public String sendEmail(String email1, RedirectAttributes redirectAttributes){
        User user = userService.getUserByEmail(email1);
        Optional<User> optional = userService.getByEmail(email1);
        if (!optional.isPresent()) {
            redirectAttributes.addFlashAttribute("message","We didn't find an account for that e-mail address.");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "login";
        }
        else {

            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setTo(user.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("Your old password was "+user.getPassword()+"\n");
            passwordResetEmail.setText("Your new password is iamstillsadu");
            emailUtil.sendEmail(passwordResetEmail);

            User resetUser = optional.get();

            resetUser.setPassword("iamstillsadu");
            userService.saveUser(resetUser);
            redirectAttributes.addFlashAttribute("message", "A link to reset the password has been sent to " + user.getEmail());
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "emailVerification";
        }
    }
}
