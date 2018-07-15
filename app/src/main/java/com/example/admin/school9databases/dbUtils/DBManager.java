package com.example.admin.school9databases.dbUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DbHelper mDbHelper;

    public DBManager(Context context) {
        this.mDbHelper = new DbHelper(context);
    }

    public void upgradeDB() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
    }

    public void addWeatherForecast(String id, String dayName, String time, String tempHight, String tempLow, String pressure) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            ContentValues contentValues = getContentValues(id, dayName, time, tempHight, tempLow, pressure);
            db.beginTransaction();
            addContentToDB(db, "WEATHER", contentValues);
            db.setTransactionSuccessful();
        } catch (SQLException e) {

        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
    }


    public List<String[]> getWeatherForWeek() {
        List notes = null;
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("WEATHER", null, null, null, null, null, "id");
            notes = parseNotesCursor(cursor);
            cursor.close();
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {

        } finally {
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
        return notes;
    }



    private ContentValues getContentValues(String id, String dayName, String time, String tempHight, String tempLow, String pressure) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("dayName", dayName);
        contentValues.put("time", time);
        contentValues.put("temperatureHight", tempHight);
        contentValues.put("temperatureLow", tempLow);
        contentValues.put("pressure", pressure);
        return contentValues;
    }



    private void addContentToDB(SQLiteDatabase db, String table, ContentValues contentValues) {
        if (table.toUpperCase().equals("WEATHER")) {
            db.replace("WEATHER", null, contentValues);
        }
    }


    private List parseNotesCursor(Cursor cursor) {
        List<String[]> weatherParams = new ArrayList();
        while (cursor.moveToNext()) {
            String[] array = new String[6];
            array[0] = cursor.getString(cursor.getColumnIndex("id"));
            array[1] = cursor.getString(cursor.getColumnIndex("dayName"));
            array[2] = cursor.getString(cursor.getColumnIndex("time"));
            array[3] = cursor.getString(cursor.getColumnIndex("temperatureHight"));
            array[4] = cursor.getString(cursor.getColumnIndex("temperatureLow"));
            array[5] = cursor.getString(cursor.getColumnIndex("pressure"));
            weatherParams.add(array);
        }
        return weatherParams;
    }

}
