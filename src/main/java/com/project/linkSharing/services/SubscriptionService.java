package com.project.linkSharing.services;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.entities.Topic;
import com.project.linkSharing.entities.User;
import com.project.linkSharing.repositories.ResourcesRepository;
import com.project.linkSharing.repositories.SubscriptionRepository;
import com.project.linkSharing.response.ResponseSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;
    @Autowired
    ResourcesRepository resourcesRepository;

    public Subscription saveSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }


    public Integer subscriptionCountByUser(User user){
        return subscriptionRepository.countByUser(user);
    }

    public List<Subscription> subscriptionListByUser(User user){
        return subscriptionRepository.findByUser(user);
    }

    public Subscription subscriptionByUserAndTopic(User user, Topic topic){
        return subscriptionRepository.findByUserAndTopic(user, topic);
    }

    public void deleteSubscription(Integer id){
        subscriptionRepository.deleteById(id);
    }

    public List<ResponseSubscription> postCountByUserAndTopic(List<Subscription> subscriptions){
        List<ResponseSubscription> responseSubscriptions = new ArrayList<>();
        for(Subscription subs: subscriptions) {
            Topic topic = subs.getTopic();
            Integer countPost = resourcesRepository.countByTopic(topic);
            Integer countSubscription = subscriptionRepository.countByTopic(topic);
            ResponseSubscription responseSubscription = new ResponseSubscription();
            responseSubscription.setId(subs.getId());
            responseSubscription.setUser(subs.getUser());
            responseSubscription.setTopic(subs.getTopic());
            responseSubscription.setPostCount(countPost);
            responseSubscription.setSubscriptionCount(countSubscription);
            responseSubscriptions.add(responseSubscription);
        }
        return responseSubscriptions;
    }
//    public List<Subscription> listSubscriptionByUserid(Integer userid){
//        return subscriptionRepository.findByUserid(userid);
//    }
//    public Integer countSubscription(Integer userid){
//        return subscriptionRepository.countByUserid(userid);
//    }
//
//    public Subscription fibdByUseridAndTopicid(Integer userid, Integer topicid){
//        return subscriptionRepository.findByUseridAndTopicid(userid, topicid);
//    }
//


}
