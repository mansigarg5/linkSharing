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
    List<Topic> findAllByDeleteFlagOrderByName(char delete);
    Optional<Topic> findByIdAndDeleteFlag(Integer id, char delete);
//    Integer countByCreatedBy(String createdBy);
//    List<Topic> findByCreatedBy(String username);
    List<Topic> findAllByVisibilityAndDeleteFlag(Visibility visibility, char delete);
    Topic findByNameAndDeleteFlag(String name, char delete);
    List<Topic> findByUserAndDeleteFlag(User user, char delete);
    Integer countByUserAndDeleteFlag(User user, char delete);
    Optional<Topic> findByIdAndUserAndDeleteFlag(Integer id, User user, char delete);
    void deleteById(Integer id);
    List<Topic> findAllByNameAndDeleteFlag(String name, char delete);
    List<Topic> findByNameInAndDeleteFlag(List<String> name, char delete);
}
