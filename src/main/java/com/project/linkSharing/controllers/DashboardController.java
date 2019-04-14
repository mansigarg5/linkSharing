package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.enums.Seriousness;
import com.project.linkSharing.enums.Visibility;
import com.project.linkSharing.response.ResponseSubscription;
import com.project.linkSharing.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    UserService userService;
    @Autowired
    TopicService topicService;
    @Autowired
    ResourcesService resourcesService;
    @Autowired
    EmailService emailService;
    @Autowired
    HttpSession session;
    @Autowired
    SubscriptionService subscriptionService;



    @GetMapping("/dashboard")
    public ModelAndView display(){
        if(session.getAttribute("user")!=null){
            User user = (User)session.getAttribute("user");
            List<Topic> topicList = topicService.listTopics();
            List<Resources> allResourcesList = resourcesService.listResources();
            Integer postCount = topicService.postCountByUser(user);
            Integer subscriptionCount = subscriptionService.subscriptionCountByUser(user, topicList);
            List<Subscription> subscriptionList = subscriptionService.subscriptionListByUser(user, topicList);
            List<ResponseSubscription> responseSubscriptions = subscriptionService.postCountByUserAndTopic(subscriptionList);
            List<Resources> resourcesList = resourcesService.listResourcesByMarkAsRead(0);
            return new ModelAndView("dashboard")
//            .addObject("image", "/images/" + user.getUsername() + ".jpeg")
//                    .addObject("topicList", topicList)
                    .addObject("subscriptionList", subscriptionList)
                    .addObject("subscriptionCount", subscriptionCount)
                    .addObject("postCount", postCount)
                    .addObject("resourcesList", resourcesList)
                    .addObject("responseSubscription", responseSubscriptions)
                    .addObject("allResourcesList", allResourcesList);
        }
        else{
            List<Topic> publicTopicList = topicService.listPublicTopic();
            List<Resources> resourcesList = resourcesService.listPublicResources(publicTopicList);
            return new ModelAndView("login").addObject("resourcesList", resourcesList);
        }
    }

//    @PostMapping("/search")
//    public String search(HttpServletRequest httpServletRequest){
//        String name = httpServletRequest.getParameter("search");
//        userService.getUserByName(name);
//        return "user";
//    }

    @PostMapping("/saveTopic")
    public String saveTopic(HttpServletRequest httpServletRequest) {
        User user = (User) session.getAttribute("user");
        String name = httpServletRequest.getParameter("name");
        String visibility = httpServletRequest.getParameter("visibility");
        Topic topic = new Topic();
        topic.setName(name);
        topic.setVisibility(Visibility.valueOf(visibility));
        topic.setUser(user);
        topic.setDate(new Date());
        topic.setSeriousness(Seriousness.VERY_SERIOUS);
        topic.setDeleteFlag('N');
        topicService.saveTopic(topic);
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setTopic(topic);
        subscriptionService.saveSubscription(subscription);
        return "redirect:/dashboard";
    }


//    @PostMapping("/saveTopic")
//    public ModelAndView saveTopic(@RequestParam Map<String, String> map){
//        User user = (User)session.getAttribute("user");
//        String name = map.get("name");
//        System.out.println(name);
//        String visibility = map.get("visibility");
//        System.out.println(visibility);
//        Topic topic = new Topic();
//        topic.setName(name);
//        topic.setVisibility(visibility);
//        topic.setSeriousness("Very serious");
//        topic.setCreatedBy(user.getUsername());
//        topic.setDate(new Date());
//        topicService.saveTopic(topic);
//        List<Topic> topicList = topicService.listTopics();
//        return new ModelAndView("dashboard").addObject("topicList", topicList);
//    }

    @PostMapping("/saveLink")
    public String saveLink(HttpServletRequest httpServletRequest){
        User user = (User)session.getAttribute("user");
        String link = httpServletRequest.getParameter("link");
        String description = httpServletRequest.getParameter("description");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        Topic topic = topicService.findTopicByName(relatedTopic);
        Resources resources = new Resources();
        resources.setLink(link);
        resources.setDescription(description);
        resources.setTopic(topic);
        resources.setUser(user);
        resources.setDate(new Date());
        resourcesService.saveLinkResources(resources);
        return "redirect:/dashboard";
    }

    @PostMapping("/saveDocument")
    public String saveDocument(@ModelAttribute("resources") Resources resources, HttpServletRequest httpServletRequest){
        User user = (User)session.getAttribute("user");
//        String document = httpServletRequest.getParameter("document");
//        String description = httpServletRequest.getParameter("description");
        String relatedTopic = httpServletRequest.getParameter("relatedTopic");
        Topic topic = topicService.findTopicByName(relatedTopic);
//        Resources resources = new Resources();
//        resources.setDescription(description);
        resources.setTopic(topic);
        resources.setUser(user);
        resources.setDate(new Date());
        resourcesService.saveUpdatedDocumentResources(resources);
        resourcesService.saveDocumentResources(resources);
        return "redirect:/dashboard";
    }

    @PostMapping("/sendInvite")
    @ResponseBody
    public String sendEmailInvite(@RequestParam("email") String email, @RequestParam("topic") String topic,
                                  RedirectAttributes redirectAttributes) throws MessagingException {
        emailService.sendInvite(email, topic, redirectAttributes);
        return "true";
    }

    @GetMapping("/logout")
    public String logoutUser(){
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }
}


