package com.project.linkSharing.enums;

public enum Seriousness {
    SERIOUS("SERIOUS"),
    CASUAL("CASUAL"),
    VERY_SERIOUS("VERY_SERIOUS");
    String value;
    Seriousness(String seriousness){
        value = seriousness;
    }
    public String getValue(){
        return value;
    }
}
