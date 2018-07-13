package com.example.admin.school9databases;


import java.util.Calendar;

public class CalendarHelper {

    public String[] getNext7DaysOfWeek() {
        String[] daysOfWeek = new String[7];
        int[] days = new int[7];
        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK);
        days[0] = currentDay;
        for (int i = 1; i < days.length; i++) {
            days[i] = (days[i - 1] + 1) % 7;
            if (days[i] == 0) {
                days[i] = 7;
            }
        }

        for (int i = 0; i < days.length; i++) {
            switch (days[i]) {
                case Calendar.SUNDAY:
                    daysOfWeek[i] = "sunday";
                    break;
                case Calendar.MONDAY:
                    daysOfWeek[i] = "monday";
                    break;
                case Calendar.TUESDAY:
                    daysOfWeek[i] = "tuesday";
                    break;
                case Calendar.FRIDAY:
                    daysOfWeek[i] = "friday";
                    break;
                case Calendar.SATURDAY:
                    daysOfWeek[i] = "saturday";
                    break;
                case Calendar.WEDNESDAY:
                    daysOfWeek[i] = "wednesday";
                    break;
                case Calendar.THURSDAY:
                    daysOfWeek[i] = "thursday";
                    break;
            }

        }
        return daysOfWeek;
    }
}
