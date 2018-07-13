package com.example.admin.school9databases.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class WeatherResponse {
    @SerializedName("daily")
    private WeatherForWeek weatherForWeek;

    public WeatherForWeek getWeatherForWeek() {
        return weatherForWeek;
    }

    public void setWeatherForWeek(WeatherForWeek weatherForWeek) {
        this.weatherForWeek = weatherForWeek;
    }


    public class WeatherForWeek {
        @SerializedName("data")
        private List<WeatherForDay> data;

        public List<WeatherForDay> getData() {
            return data;
        }

        public void setData(List<WeatherForDay> data) {
            this.data = data;
        }

    }
}