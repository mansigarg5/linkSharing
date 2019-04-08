package com.project.linkSharing.services;

import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.UserService;
import com.project.linkSharing.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class EmailService {

    @Autowired
    UserService userService;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    HttpSession session;

    public String sendEmail(String email1, RedirectAttributes redirectAttributes) throws MessagingException {
        User user = userService.getUserByEmail(email1);
        session.setAttribute("user", user);
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
            passwordResetEmail.setText("Please click on the link below to reset your password:" +
                    "\nhttp://localhost:8080/emailVerification");

//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setContent("<a>http://localhost:8080/emailVerification</a>", "text/html");
            emailUtil.sendEmail(passwordResetEmail);


//            User resetUser = optional.get();
//
//            resetUser.setPassword();
//            userService.saveUser(resetUser);
            redirectAttributes.addFlashAttribute("message", "A link to reset the password has been sent to " + user.getEmail());
            redirectAttributes.addFlashAttribute("alertClass", "alert-success");
            return "emailVerification";
        }
    }
}
