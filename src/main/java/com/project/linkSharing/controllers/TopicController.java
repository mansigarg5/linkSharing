package com.project.linkSharing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopicController {

    @GetMapping("/topic")
    public String display(){
        return "topic";
    }
}
