package com.project.linkSharing.services;

import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public Topic saveTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public List<Topic> listTopics(){
        return topicRepository.findAll();
    }
}
