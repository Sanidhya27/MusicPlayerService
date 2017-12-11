package com.example.android.moodindigo.EventsDatabase;

/**
 * Created by mrunz on 7/12/17.
 */

public class Tag {

    private long id;
    private String type;
    private int day;

    public Tag(){}


    public Tag(long id, String type,int day){
        this.id=id;
        this.type=type;
        this.day=day;
    }
    public Tag( String type,int day){
        this.type=type;
        this.day=day;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
