package com.project.linkSharing.response;

import com.project.linkSharing.entities.Topic;

import javax.persistence.Table;

public class ResponseTopic {
    private Topic topic;
    private Integer resourcesCount;

    public Integer getResourcesCount() {
        return resourcesCount;
    }

    public void setResourcesCount(Integer resourcesCount) {
        this.resourcesCount = resourcesCount;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
