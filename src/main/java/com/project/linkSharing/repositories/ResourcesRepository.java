package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Resources;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.response.ResponseTopic;
import com.project.linkSharing.services.TopicService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ResourcesRepository extends CrudRepository<Resources, Integer> {
    Resources save(Resources resources);
    List<Resources> findFirst3ByTopicIn(List<Topic> topicIdList);
    List<Resources> findAll();
    Optional<Resources> findById(Integer id);
    List<Resources> findAllByMarkAsRead(Integer markAsRead);
    Integer countByTopic(Topic topic);
    Integer countByUserAndTopic(User user, Topic topic);
    List<Resources> findAllByTopicIn(List<Topic> topicList);
    @Query("select r.topic, count(r) from Resources r group by r.topic")
    List<Resources> countByTopic();


//    Map<Topic, Integer> countByTopic();
}
