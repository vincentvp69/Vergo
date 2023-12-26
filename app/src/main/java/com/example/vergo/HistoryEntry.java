package com.example.vergo;

public class HistoryEntry {
    private String title;
    private String location;
    private String date;
    private String time;
    private int maxPersons;
    private int imageResourceId;

    public HistoryEntry(String title, String location, String date, String time, int maxPersons, int imageResourceId) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.maxPersons = maxPersons;
        this.imageResourceId = imageResourceId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getMaxPersons() {
        return maxPersons;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setMaxPersons(int maxPersons) {
        this.maxPersons = maxPersons;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
// Constructors, getters, and setters
}
