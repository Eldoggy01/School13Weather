package com.example.admin.school9databases.Retrofit;

import com.example.admin.school9databases.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DarkSkyApiMapper {
    private DarkSkyRetrofitHelper helper;
    private WeatherResponse weatherResponse;

    public DarkSkyApiMapper(DarkSkyRetrofitHelper helper) {
        this.helper = helper;
    }


    public void getAsync() {
        helper.getService().getDailyForecast(DarkSkyRetrofitHelper.API_KEY, DarkSkyRetrofitHelper.LATITUDE, DarkSkyRetrofitHelper.LONGITUDE).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                weatherResponse = response.body();
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }
}