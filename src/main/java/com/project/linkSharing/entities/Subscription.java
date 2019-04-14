package com.project.linkSharing.entities;

import com.project.linkSharing.enums.Seriousness;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//@NamedNativeQuery(name = "Subscription.findByUser",
//        query = "SELECT * from Subscription where user = :user",
//        resultClass = Subscription.class)
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Topic topic;
//    private Integer userid;
//    private Integer topicid;
//    private String visibility;
//    private Seriousness seriousness;

//    public Integer getUserid() {
//        return userid;
//    }
//
//    public void setUserid(Integer userid) {
//        this.userid = userid;
//    }
//
//    public Integer getTopicid() {
//        return topicid;
//    }
//
//    public void setTopicid(Integer topicid) {
//        this.topicid = topicid;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }


//    public List<User> getUser() {
//        return userList;
//    }
//
//    public void setUser(List<User> userList) {
//        this.userList = userList;
//    }
//
//    public List<Topic> getTopic() {
//        return topicList;
//    }
//
//    public void setTopic(List<Topic> topic) {
//        this.topicList = topic;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getVisibility() {
//        return visibility;
//    }
//
//    public void setVisibility(String visibility) {
//        this.visibility = visibility;
//    }

//    public Seriousness getSeriousness() {
//        return seriousness;
//    }
//
//    public void setSeriousness(Seriousness seriousness) {
//        this.seriousness = seriousness;
//    }
}
