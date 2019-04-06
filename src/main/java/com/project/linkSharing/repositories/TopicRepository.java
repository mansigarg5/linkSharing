package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Topic save(Topic topic);
    List<Topic> findAll();
}
