package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ResourcesRepository extends CrudRepository<Resources, Integer> {
    Resources save(Resources resources);
    List<Resources> findByRelatedTopicIn(List<String> relatedTopicList);
}
