package com.example.android.moodindigo.EventsDatabase;

/**
 * Created by mrunz on 7/12/17.
 */

public class Event {
    private int id;
    private String name;
    private String description;
    private String venue;
    private String time_start;
    private int going_total;
    private int going;

    public Event(){}

    public Event(String name,String description,String venue,String time_start,int going_total){
        this.name=name;
        this.description=description;
        this.venue=venue;
        this.time_start=time_start;
        this.going=0;
        this.going_total=going_total;
    }
    public Event(int id,String name,String description,String venue,String time_start,int going_total){
        this.id=id;
        this.name=name;
        this.description=description;
        this.venue=venue;
        this.time_start=time_start;
        this.going=0;
        this.going_total=going_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public int getGoing() {
        return going;
    }

    public void setGoing(int going) {
        this.going = going;
    }



    public int getGoing_total() {
        return going_total;
    }

    public void setGoing_total(int going_total) {
        this.going_total = going_total;
    }
}
