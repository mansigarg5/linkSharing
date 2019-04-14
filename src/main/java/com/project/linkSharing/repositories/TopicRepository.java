package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.enums.Visibility;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Integer> {
    Topic save(Topic topic);
    List<Topic> findAllByOrderByName();
    Optional<Topic> findById(Integer id);
//    Integer countByCreatedBy(String createdBy);
//    List<Topic> findByCreatedBy(String username);
    List<Topic> findAllByVisibility(Visibility visibility);
    Topic findByName(String name);
    List<Topic> findByUser(User user);
    Integer countByUser(User user);
    Optional<Topic> findByIdAndUser(Integer id, User user);
    void deleteById(Integer id);
    List<Topic> findAllByName(String name);
}
