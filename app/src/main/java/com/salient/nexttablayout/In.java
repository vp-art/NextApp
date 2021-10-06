package com.salient.nexttablayout;

public class In {
    private String Name;
    private String Type;

    public In(){

    }

    public In(String name, String type) {
        Name = name;
        Type = type;
    }
    // Getter

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    //Setter

    public void setName(String name) {
        Name = name;
    }

    public void setType(String type) {
        Type = type;
    }
}
