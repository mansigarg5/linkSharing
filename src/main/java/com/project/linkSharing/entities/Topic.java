package com.project.linkSharing.entities;

import com.project.linkSharing.enums.Seriousness;
import com.project.linkSharing.enums.Visibility;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Visibility visibility;
    private Seriousness seriousness;
    @ManyToOne
    private User user;
//    @OneToMany(mappedBy = "topic")
//    private List<Subscription> subscriptions = new ArrayList<>();
//    @OneToMany(mappedBy = "topic")
//    private List<Resources> resources = new ArrayList<>();
    @Temporal(TemporalType.DATE)
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public Seriousness getSeriousness() {
        return seriousness;
    }

    public void setSeriousness(Seriousness seriousness) {
        this.seriousness = seriousness;
    }

    //
//    public String getSeriousness() {
//        return seriousness;
//    }
//
//    public void setSeriousness(String seriousness) {
//        this.seriousness = seriousness;
//    }


//    public List<Resources> getResources() {
//        return resources;
//    }
//
//    public void setResources(List<Resources> resources) {
//        this.resources = resources;
//    }

//    public List<Subscription> getSubscriptions() {
//        return subscriptions;
//    }
//
//    public void setSubscriptions(List<Subscription> subscriptions) {
//        this.subscriptions = subscriptions;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
