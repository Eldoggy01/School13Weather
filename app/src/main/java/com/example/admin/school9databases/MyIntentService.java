package com.example.admin.school9databases;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.admin.school9databases.model.WeatherForDay;
import com.example.admin.school9databases.dbUtils.DBManager;
import com.example.admin.school9databases.retrofit.DarkSkyApiMapper;
import com.example.admin.school9databases.retrofit.DarkSkyRetrofitHelper;

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
        CalendarHelper calendarHelper = new CalendarHelper();
        String[] daysOfWeek = calendarHelper.getNext7DaysOfWeek();
        for (int i = 0; i < daysOfWeek.length && i < weatherForDayList.size(); i++) {
            WeatherForDay weatherForDay = weatherForDayList.get(i);
            dbManager.addWeatherForecast(daysOfWeek[i], weatherForDay.getTime().toString(), weatherForDay.getTemperatureHigh().toString(), weatherForDay.getTemperatureLow().toString(), weatherForDay.getPressure().toString());
            Log.d("GGGG",dbManager.getWeatherForWeek().get(0).toString());
        }

    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MyIntentService.class);
    }
}