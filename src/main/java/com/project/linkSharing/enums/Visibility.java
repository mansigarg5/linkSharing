package com.project.linkSharing.enums;

public enum Visibility {
    PUBLIC("PUBLIC"),
    PRIVATE("PRIVATE");
    String value;
    Visibility(String visibility){
        value = visibility;
    }
    public String getValue(){
        return value;
    }
}
