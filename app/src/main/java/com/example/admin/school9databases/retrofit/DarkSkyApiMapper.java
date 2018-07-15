package com.example.admin.school9databases.retrofit;

import android.util.Log;

import com.example.admin.school9databases.MainActivity;
import com.example.admin.school9databases.model.WeatherResponse;

import java.io.IOException;

import retrofit2.Response;

public class DarkSkyApiMapper {
    private DarkSkyRetrofitHelper helper;
    private WeatherResponse weatherResponse;

    public DarkSkyApiMapper(DarkSkyRetrofitHelper helper) {
        this.helper = helper;
    }

    public void getWeatherSync() throws IOException {
        Log.d(MainActivity.logTag, "Зашли в getWeatherSync()");
        Response<WeatherResponse> response =   helper.getService().getDailyForecast(DarkSkyRetrofitHelper.API_KEY, DarkSkyRetrofitHelper.LATITUDE, DarkSkyRetrofitHelper.LONGITUDE).execute();
        weatherResponse = response.body();
    }


    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }
}