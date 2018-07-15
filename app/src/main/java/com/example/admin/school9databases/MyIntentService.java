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

import java.io.IOException;
import java.util.List;

public class MyIntentService extends IntentService {
    private DarkSkyRetrofitHelper retrofitHelper;
    private DarkSkyApiMapper apiMapper;
    private DBManager dbManager;

    public MyIntentService() {
        super("MyIntentService");

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("GG", "Зашли в onHandleIntent");
        retrofitHelper = new DarkSkyRetrofitHelper();
        apiMapper = new DarkSkyApiMapper(retrofitHelper);
        try {
            apiMapper.getWeatherSync();
            List<WeatherForDay> weatherForDayList = apiMapper.getWeatherResponse().getWeatherForWeek().getData();
            dbManager = new DBManager(this);
            CalendarHelper calendarHelper = new CalendarHelper();
            String[] daysOfWeek = calendarHelper.getNext7DaysOfWeek();
            for (int i = 0; i < daysOfWeek.length && i < weatherForDayList.size(); i++) {
                WeatherForDay weatherForDay = weatherForDayList.get(i);
                dbManager.addWeatherForecast(String.valueOf(i + 1), daysOfWeek[i], weatherForDay.getTime().toString(), weatherForDay.getTemperatureHigh().toString(), weatherForDay.getTemperatureLow().toString(), weatherForDay.getPressure().toString());
            }
            Intent broadcastIntent = new Intent(MainActivity.INTENT_FILTER);
            broadcastIntent.putExtra("data", "success");
            sendBroadcast(broadcastIntent);
        } catch (IOException e) {
            Intent broadcastIntent = new Intent(MainActivity.INTENT_FILTER);
            broadcastIntent.putExtra("data", "failure");
            sendBroadcast(broadcastIntent);
            e.printStackTrace();
        }
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MyIntentService.class);
    }
}