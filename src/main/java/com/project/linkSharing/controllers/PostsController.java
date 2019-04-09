package com.project.linkSharing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostsController {

    @GetMapping("/posts")
    public String display(){
        return "post";
    }
}
