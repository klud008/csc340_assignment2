package com.csc340.demo;

public class WeatherReport {
    public String query;
    public String time;
    public String weather;

    public WeatherReport(String query, String time, String weather) {
        this.query = query;
        this.time = time;
        this.weather = weather;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery (String query) {
        this.query = query;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return("Query: " + query + ", Time: " + time + ", Weather: " + weather);
    }
}
