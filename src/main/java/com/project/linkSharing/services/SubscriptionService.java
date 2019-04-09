package com.project.linkSharing.services;

import com.project.linkSharing.entities.Subscription;
import com.project.linkSharing.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;
    public Subscription saveSubscription(Subscription subscription){
        return subscriptionRepository.save(subscription);
    }
    public List<Subscription> listSubscriptionByUserid(Integer userid){
        return subscriptionRepository.findByUserid(userid);
    }
    public Integer countSubscription(Integer userid){
        return subscriptionRepository.countByUserid(userid);
    }

}
