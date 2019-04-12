package com.android.duongdb.weather.models;

import java.util.ArrayList;

public class Weather
{
    private ArrayList<ForecastWeather> forecastWeathers;
    private String cityName;
    private String countryName;
    private String updateTime;
    private int temp;
    private int condCode;
    private String condText;
    private int windSpeed; //km/h - kilometer per hour
    private String windDirection;
    private int humidity; //% - percentage
    private Double pressure; //mb - milibar
    private Double visibility; //km - kilometer
    private String sunrise;
    private String sunset;

    public ArrayList<ForecastWeather> getForecastWeathers() {
        return forecastWeathers;
    }

    public void setForecastWeathers(ArrayList<ForecastWeather> forecastWeathers) {
        this.forecastWeathers = forecastWeathers;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getCondCode() {
        return condCode;
    }

    public void setCondCode(int condCode) {
        this.condCode = condCode;
    }

    public String getCondText() {
        return condText;
    }

    public void setCondText(String condText) {
        this.condText = condText;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }
}
