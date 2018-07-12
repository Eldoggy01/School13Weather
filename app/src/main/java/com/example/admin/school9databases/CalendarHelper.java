package com.example.admin.school9databases;


import java.util.Calendar;

public class CalendarHelper {

    public  int[] getNext7DaysOfWeek() {
        int[] days = new int[7];
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        days[0] = currentDay;
        for (int i = 1; i < days.length; i++) {
            days[i] = (days[i-1]+1)%7;
        }
        return days;
    }
}
