package com.example.admin.school9databases.retrofit;


import com.example.admin.school9databases.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DarkSkyWebService {

    @GET("forecast/{key}/{latitude},{longitude}")
    Call<WeatherResponse> getDailyForecast(@Path("key") String apiKey, @Path("latitude") String latitude, @Path("longitude") String longitude);
}

