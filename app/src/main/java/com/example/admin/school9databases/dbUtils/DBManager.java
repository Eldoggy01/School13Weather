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

    public void addWeatherForecast(String dayName, String time, String tempHight, String tempLow, String pressure) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            ContentValues contentValues = getContentValues(dayName, time, tempHight, tempLow, pressure);
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

//    public void addNote(String note) {
//        SQLiteDatabase db = null;
//        try {
//            db = mDbHelper.getWritableDatabase();
//            ContentValues contentValues = getNoteContentValues(null, note);
//            db.beginTransaction();
//            addContentToDB(db, "NOTES", contentValues);
//            db.setTransactionSuccessful();
//        } catch (SQLException e) {
//
//        } finally {
//            if (db != null) {
//                if (db.inTransaction()) {
//                    db.endTransaction();
//                }
//                db.close();
//            }
//        }
//    }


//    public void updateNoteInDB(String id, String note) {
//        SQLiteDatabase database;
//        ContentValues contentValues = getNoteContentValues(id, note);
//        database = mDbHelper.getWritableDatabase();
//        database.update("NOTES", contentValues, "id=?", new String[]{id});
//        database.close();
//    }

    public void updateStyleInDB(String id, String color) {
        SQLiteDatabase database;
        ContentValues contentValues = getStyleContentValues(id, color);
        database = mDbHelper.getWritableDatabase();
        database.update("STYLES", contentValues, "id=?", new String[]{id});
        database.close();
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

    public List<String> getStyle() {
        List style = null;
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("STYLES", null, null, null, null, null, "id");
            style = parseStyleCursor(cursor);
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
        return style;
    }


    private ContentValues getContentValues(String dayName, String time, String tempHight, String tempLow, String pressure) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("dayName", dayName);
        contentValues.put("time", time);
        contentValues.put("temperatureHight", tempHight);
        contentValues.put("temperatureLow", tempLow);
        contentValues.put("pressure", pressure);
        return contentValues;
    }


    private ContentValues getStyleContentValues(String id, String color) {
        ContentValues contentValues = new ContentValues();
        if (id != null) {
            contentValues.put("id", id);
        }
        if (color != null) {
            contentValues.put("color", color);
        }
        return contentValues;
    }


    private void addContentToDB(SQLiteDatabase db, String table, ContentValues contentValues) {
        if (table.toUpperCase().equals("WEATHER")) {
            db.insert("WEATHER", null, contentValues);
        }
    }


    private List parseNotesCursor(Cursor cursor) {
        List<String[]> weatherParams = new ArrayList();
        while (cursor.moveToNext()) {
            String[] array = new String[6];
            array[1] = cursor.getString(cursor.getColumnIndex("id"));
            array[2] = cursor.getString(cursor.getColumnIndex("dayName"));
            array[3] = cursor.getString(cursor.getColumnIndex("time"));
            array[4] = cursor.getString(cursor.getColumnIndex("temperatureHight"));
            array[5] = cursor.getString(cursor.getColumnIndex("temperatureLow"));
            array[6] = cursor.getString(cursor.getColumnIndex("pressure"));
            weatherParams.add(array);
        }
        return weatherParams;
    }

    private List parseStyleCursor(Cursor cursor) {
        List<String> style = new ArrayList();
        while (cursor.moveToNext()) {
            style.add(cursor.getString(cursor.getColumnIndex("id")));
            style.add(cursor.getString(cursor.getColumnIndex("color")));
        }
        return style;
    }
}
