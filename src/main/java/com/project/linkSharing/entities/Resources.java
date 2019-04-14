package com.project.linkSharing.entities;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String link;
    @Transient
    private MultipartFile document;
    private String documentName;
    private String description;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Topic topic;
    @ManyToOne
    private User user;
    private Date date;
    private int markAsRead;
//    private String relatedTopic;
//    private String createdBy;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMarkAsRead() {
        return markAsRead;
    }

    public void setMarkAsRead(int markAsRead) {
        this.markAsRead = markAsRead;
    }

//    public String getRelatedTopic() {
//        return relatedTopic;
//    }
//
//    public void setRelatedTopic(String relatedTopic) {
//        this.relatedTopic = relatedTopic;
//    }
    public Date getDate() {
    return date;
}

    public void setDate(Date date) {
        this.date = date;
    }


    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }


    @Override
    public String toString() {
        return "Resources{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", document=" + document +
                ", documentName='" + documentName + '\'' +
                ", description='" + description + '\'' +
                ", topic=" + topic +
                ", user=" + user +
                ", date=" + date +
                ", markAsRead=" + markAsRead +
                '}';
    }
}
