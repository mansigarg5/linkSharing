package com.project.linkSharing.services;

import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.enums.Visibility;
import com.project.linkSharing.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public Topic saveTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public List<Topic> listTopics(){
        return topicRepository.findAllByOrderByName();
    }

    public Topic findTopicByName(String name){
        return topicRepository.findByName(name);
    }

    public List<Topic> topicCreatedByUser(User user){
        return topicRepository.findByUser(user);
    }

    public Integer postCountByUser(User user){
        return topicRepository.countByUser(user);
    }

    public Optional<Topic> findTopicById(Integer topicid){
        return topicRepository.findById(topicid);
    }

    public Optional<Topic> findTopicByIdAndUser(Integer topicId, User user){
        return topicRepository.findByIdAndUser(topicId, user);
    }

    public List<Topic> listPublicTopic(){
        return topicRepository.findAllByVisibility(Visibility.PUBLIC);
    }

    public void deleteTopic(Integer topicId){
        topicRepository.deleteById(topicId);
    }

    public List<Topic> findAllTopicByName(String name){
        return topicRepository.findAllByName(name);
    }

//    public List<Topic> topicListByUser(String username){
//        return topicRepository.findByCreatedBy(username);
//    }
}
