package com.project.linkSharing.services;


import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.repositories.ResourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourcesService {

    @Autowired
    ResourcesRepository resourcesRepository;
    public Resources saveResources(Resources resources){
        return resourcesRepository.save(resources);
    }

    public List<Resources> listPublicResources(List<String> relatedTopicList){
        return resourcesRepository.findByRelatedTopicIn(relatedTopicList);
    }
}
