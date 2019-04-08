package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Topic save(Topic topic);
    List<Topic> findAll();
    @Query("select name from Topic where visibility = :visibility")
    List<String> findByVisibility(@Param("visibility") String visibility);
}
