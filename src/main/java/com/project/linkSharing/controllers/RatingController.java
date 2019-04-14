package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Rating;
import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.RatingService;
import com.project.linkSharing.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class RatingController {
    @Autowired
    HttpSession session;
    @Autowired
    ResourcesService resourcesService;
    @Autowired
    RatingService ratingService;

    @PostMapping("/setRating")
    @ResponseBody
    public String display(@RequestParam("resourceId") Integer resourceId, @RequestParam("rating") Integer ratings){
        User user = (User)session.getAttribute("user");
        Optional<Resources> optionalResources = resourcesService.findResourceById(resourceId);
        if(optionalResources.isPresent()){
            Resources resources = optionalResources.get();
            Rating rating = new Rating();
            rating.setUser(user);
            rating.setResources(resources);
            rating.setRatings(ratings);
            ratingService.saveRating(rating);
        }
        return "true";
    }

    @PostMapping("/editPostDescription")
    @ResponseBody
    public String editDescription(@RequestParam("resourceId") Integer resorceId,
                                   @RequestParam("newName") String newName){
        Optional<Resources> optionalResources = resourcesService.findResourceById(resorceId);
        if(optionalResources.isPresent()){
            Resources resources = optionalResources.get();
            resources.setDescription(newName);
            resourcesService.saveResources(resources);
        }
        return "true";
    }
}
