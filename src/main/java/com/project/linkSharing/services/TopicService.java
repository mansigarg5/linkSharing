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
        return topicRepository.findAllByDeleteFlagOrderByName('N');
    }

    public Topic findTopicByName(String name){
        return topicRepository.findByNameAndDeleteFlag(name, 'N');
    }

    public List<Topic> topicCreatedByUser(User user){
        return topicRepository.findByUserAndDeleteFlag(user, 'N');
    }

    public Integer postCountByUser(User user){
        return topicRepository.countByUserAndDeleteFlag(user, 'N');
    }

    public Optional<Topic> findTopicById(Integer topicid){
        return topicRepository.findByIdAndDeleteFlag(topicid, 'N');
    }

    public Optional<Topic> findTopicByIdAndUser(Integer topicId, User user){
        return topicRepository.findByIdAndUserAndDeleteFlag(topicId, user, 'N');
    }

    public List<Topic> listPublicTopic(){
        return topicRepository.findAllByVisibilityAndDeleteFlag(Visibility.PUBLIC, 'N');
    }

    public void deleteTopic(Integer topicId){
        topicRepository.deleteById(topicId);
    }

    public List<Topic> findAllTopicByName(String name){
        return topicRepository.findAllByNameAndDeleteFlag(name, 'N');
    }

    public List<Topic> findTopicsByName(List<String> name){
        return topicRepository.findByNameInAndDeleteFlag(name, 'N');
    }

//    public List<Topic> topicListByUser(String username){
//        return topicRepository.findByCreatedBy(username);
//    }
}
