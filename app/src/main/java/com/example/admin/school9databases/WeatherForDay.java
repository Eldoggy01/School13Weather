package com.example.admin.school9databases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherForDay  {
    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("temperatureHigh")
    @Expose
    private Double temperatureHigh;
    @SerializedName("temperatureHighTime")
    @Expose
    private Integer temperatureHighTime;
    @SerializedName("temperatureLow")
    @Expose
    private Double temperatureLow;
    @SerializedName("temperatureLowTime")
    @Expose
    private Integer temperatureLowTime;
    @SerializedName("pressure")
    @Expose
    private Double pressure;


    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(Double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public Integer getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public void setTemperatureHighTime(Integer temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
    }

    public Double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(Double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public Integer getTemperatureLowTime() {
        return temperatureLowTime;
    }


    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

}