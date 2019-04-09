package com.project.linkSharing.repositories;

import com.project.linkSharing.entities.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    Subscription save(Subscription subscription);
    @Query("select s from Subscription s where userid = :userid")
    List<Subscription> findByUserid(@Param("userid") Integer userid);
    Integer countByUserid(Integer userid);

}
