package com.project.linkSharing.emailSending;

import com.project.linkSharing.entities.User;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@Component
public class EmailService {
    public void sendmail(User user) throws AddressException, MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("gargmansi115@gmail.com", "thisandthat");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("gargmansi115@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("gargmansi115@gmail.com"));
        msg.setSubject(user.getPassword());
        msg.setContent("This is your password" + user.getPassword(), "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Forgot Password", "text/html");

//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/var/tmp/image19.png");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
        Transport.send(msg);
    }
}
