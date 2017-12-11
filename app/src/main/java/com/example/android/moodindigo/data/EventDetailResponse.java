package com.example.android.moodindigo.data;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mrunz on 17/9/17.
 */

public class EventDetailResponse {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("going")
    private int going;
    @SerializedName("venue")
    private String venue;
    @SerializedName("time")
    private String time;
    @SerializedName("type")
    private String type;
    @SerializedName("day")
    private int day;

    public EventDetailResponse() {
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getGoing() {
        return going;
    }

    public void setGoing(int going) {
        this.going = going;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}