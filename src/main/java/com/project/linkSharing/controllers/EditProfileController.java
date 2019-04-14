package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.response.ResponseSubscription;
import com.project.linkSharing.services.SubscriptionService;
import com.project.linkSharing.services.TopicService;
import com.project.linkSharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class EditProfileController {

    @Autowired
    HttpSession session;
    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/editProfile")
    public ModelAndView display(){
        User user = (User) session.getAttribute("user");
        List<Topic> topicList = topicService.topicCreatedByUser(user);
        Integer postCount = topicService.postCountByUser(user);
        Integer subscriptionCount = subscriptionService.subscriptionCountByUser(user, topicList);
        List<Topic> topicList1 = topicService.listTopics();
        List<Subscription> subscriptionList = subscriptionService.subscriptionListByUser(user, topicList1);
        List<ResponseSubscription> responseSubscriptions = subscriptionService.postCountByUserAndTopic(subscriptionList);
        return new ModelAndView("editProfile").addObject("topicList", topicList)
                .addObject("postCount", postCount)
                .addObject("subscriptionCount", subscriptionCount)
                .addObject("subscriptionList", subscriptionList)
                .addObject("responseSubscription", responseSubscriptions);
    }

    @PostMapping("/changeProfile")
    public String displayProfile(HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUsername(request.getParameter("username"));
        userService.saveUpdatedUser(user);
        return "redirect:/editProfile";
    }

    @PostMapping("/changePassword")
    public String display(HttpServletRequest httpServletRequest){
        String password = httpServletRequest.getParameter("password");
        String confirmPassword = httpServletRequest.getParameter("confirmPassword");
        if(password.equals(confirmPassword)){
            User user = (User)session.getAttribute("user");
            user.setPassword(password);
            userService.saveUpdatedUser(user);
            return "redirect:/editProfile";
        }
        else{
            return "login";
        }
    }
}
