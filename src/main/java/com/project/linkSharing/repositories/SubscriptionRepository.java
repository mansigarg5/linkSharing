package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    Subscription save(Subscription subscription);
    Integer countByUserAndTopicIn(User user, List<Topic> topicList);
    List<Subscription> findByUserAndTopicIn(User user, List<Topic> topicList);
    Subscription findByUserAndTopic(User user, Topic topic);
    void deleteById(Integer id);
    Integer countByTopic(Topic topic);
    List<Subscription> findAll();
    List<Subscription> findByTopicIn(List<Topic> topicList);
//    List<Subscription> findByUser(User user);
//    @Query("select count(*) from  subscription_user_list where user_id = :user_id")
//    Integer countByUser(@Param("user_id") Integer userid);
//    List<Subscription> findByUser(@Param("user") User user);
//    @Query("select topic from Subscription where userid: ")
//    List<Integer> findByUserAndTopic(Integer user, Integer topic);
////    @Query("select s from Subscription s where userid = :userid")
//    List<Subscription> findByUserid(@Param("userid") Integer userid);
//    Integer countByUserid(Integer userid);
//    Subscription findByUseridAndTopicid(Integer userid, Integer topicid);


}
