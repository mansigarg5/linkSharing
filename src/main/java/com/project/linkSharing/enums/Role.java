package com.project.linkSharing.enums;

public enum Role {
    ADMIN("ADMIN"),
    USER("USER");
    String value;
    Role(String role){
        value = role;
    }
    public String getValue(){
        return value;
    }
}
