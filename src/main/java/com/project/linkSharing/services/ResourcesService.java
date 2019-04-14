package com.project.linkSharing.services;


import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.repositories.ResourcesRepository;
import com.project.linkSharing.response.ResponseTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ResourcesService {

    @Autowired
    ResourcesRepository resourcesRepository;
    private static final String dirpath2 = "/home/ttn/linkSharing/src/main/resources/static/documents";
    public Resources saveLinkResources(Resources resources){
        return resourcesRepository.save(resources);
    }

    public Resources saveUpdatedDocumentResources(Resources resources){
        return resourcesRepository.save(resources);
    }

    public Resources saveDocumentResources(Resources resources){
            Path path = Paths.get(dirpath2, resources.getId() + ".txt");
            try {
                path = Files.write(path, resources.getDocument().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            resources.setDocumentName(path.toString());
            return resourcesRepository.save(resources);
    }

    public Resources saveResources(Resources resources){
        return resourcesRepository.save(resources);
    }

    public List<Resources> listPublicResources(List<Topic> topicIdList){
        return resourcesRepository.findFirst3ByTopicIn(topicIdList);
    }

    public List<Resources> listResources(){
        return resourcesRepository.findAll();
    }

    public Optional<Resources> findResourceById(Integer resourceid){
        return resourcesRepository.findById(resourceid);
    }

    public List<Resources> listResourcesByMarkAsRead(Integer markAsRead){
        return resourcesRepository.findAllByMarkAsRead(markAsRead);
    }

    public Integer countResourcesByTopic(Topic topic){
        return resourcesRepository.countByTopic(topic);
    }

    public List<Resources> findResourcesByTopicName(List<Topic> topicList){
        return resourcesRepository.findAllByTopicIn(topicList);
    }

//    public void countMaxResourcesByTopic(){
//        List<Resources> list = resourcesRepository.countByTopic();
//        System.out.println(list.get(0).getTopic().getId());
////        System.out.println(list.get(0).);
//        System.out.println(list.get(1));
//    }

}
