package com.example.admin.school9databases.Retrofit;

import com.example.admin.school9databases.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DarkSkyApiMapper  {
    private DarkSkyRetrofitHelper helper;

    public DarkSkyApiMapper(DarkSkyRetrofitHelper helper) {
        this.helper = helper;
    }

public void getAsync(String apiKey, String latitude, String longitude){
        helper.getService().getDailyForecast(apiKey,latitude,longitude).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {

            }
        });
}
}