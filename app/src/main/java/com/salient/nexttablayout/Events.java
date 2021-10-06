package com.salient.nexttablayout;

public class Events {
    private String Event;
    private String Date;

    public Events(){

    }

    public Events(String event, String date) {
        Event = event;
        Date = date;
    }
    // Getter

    public String getEvent() {
        return Event;
    }

    public String getDate() {
        return Date;
    }

    //Setter

    public void setName(String name) {
        Event = name;
    }

    public void setDate(String date) {
        Date = date;
    }
}

