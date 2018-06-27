package com.example.admin.school9databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DbHelper dbHelper;

    public DBManager(Context context){
        this.dbHelper = new DbHelper(context);
    }

    public String addNote(String note){
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getWritableDatabase();
            ContentValues content;

        }catch(SQLException e){

        }
    }

    private ContentValues getContentValuse(String notes){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTE",notes);
    }
}
