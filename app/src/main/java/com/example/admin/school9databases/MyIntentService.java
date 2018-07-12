package com.example.admin.school9databases;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.admin.school9databases.Retrofit.DarkSkyApiMapper;
import com.example.admin.school9databases.Retrofit.DarkSkyRetrofitHelper;

import java.util.List;

public class MyIntentService extends IntentService {
    private DarkSkyRetrofitHelper retrofitHelper;
    private DarkSkyApiMapper apiMapper;
    private DBManager dbManager;

    public MyIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        retrofitHelper = new DarkSkyRetrofitHelper();
        apiMapper = new DarkSkyApiMapper(retrofitHelper);
        apiMapper.getAsync();
        List<WeatherForDay> weatherForDayList = apiMapper.getWeatherResponse().getWeatherForWeek().getData();
        dbManager = new DBManager(this);

        for (int i = 0; i <weatherForDayList.size() ; i++) {

        }

    }
}