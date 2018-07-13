package com.example.admin.school9databases.retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DarkSkyRetrofitHelper {
    private static final String BASE_URL = "https://api.darksky.net";
    public static final String API_KEY = "b12db67cff507d4f95a1e0c1d1f6f968";
    public static final String LATITUDE = "42.3601";
    public static final String LONGITUDE = "-71.0589";
    public DarkSkyWebService getService(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return  retrofit.create(DarkSkyWebService.class);
    }

}