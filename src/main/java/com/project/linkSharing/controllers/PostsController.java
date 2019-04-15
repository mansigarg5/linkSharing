package com.project.linkSharing.controllers;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.services.ResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Controller
public class PostsController {

    @Autowired
    ResourcesService resourcesService;
    @Autowired
    HttpSession session;

    @GetMapping("/posts")
    public ModelAndView posts(){
        List<Resources> resourcesList = resourcesService.listResources();
        return new ModelAndView("posts").addObject("resourcesList", resourcesList);
    }

    @GetMapping("/post/{id}")
    public ModelAndView display(@PathVariable("id") Integer resourcesid){
        Optional<Resources> optionalResources = resourcesService.findResourceById(resourcesid);
        Resources resources = optionalResources.get();
        List<Resources> allResourcesList = resourcesService.listResources();
        return new ModelAndView("post").addObject("resources", resources)
                .addObject("allResourcesList", allResourcesList);

    }


    @PostMapping("/markAsRead")
    @ResponseBody
    public int markAsRead(@RequestParam Integer resourceId){
        Optional<Resources> optionalResources = resourcesService.findResourceById(resourceId);
        if(optionalResources.isPresent()) {
            Resources resources = optionalResources.get();
            resources.setMarkAsRead(1);
            resourcesService.saveResources(resources);
        }
        return 1;
    }
}
