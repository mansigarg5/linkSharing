package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Topic;
import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Topic save(Topic topic);
}
