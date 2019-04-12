package com.android.duongdb.weather.models;

public class HistoryModel {
    private String name;
    private String city;
    private String cencius;
    private String date;
    private int imageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCencius() {
        return cencius;
    }

    public void setCencius(String cencius) {
        this.cencius = cencius;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
