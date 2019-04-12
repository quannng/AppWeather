package com.android.duongdb.weather.models;

public class WeatherModel {
    private String name;
    private int imageId;
    private String cencius;

    public WeatherModel(String name, int imageId, String cencius) {
        this.name = name;
        this.imageId = imageId;
        this.cencius = cencius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCencius() {
        return cencius;
    }

    public void setCencius(String cencius) {
        this.cencius = cencius;
    }
}
