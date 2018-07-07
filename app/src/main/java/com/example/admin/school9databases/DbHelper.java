package com.example.admin.school9databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION_DB = 1;
    private static final String DB_NAME = "database.db";


    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public DbHelper(Context context) {
        this(context, DB_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("KG", "Зашли в onCreate");
        createEmptyTables(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("KG", "onUpgrade вызвался");
        deleteTables(sqLiteDatabase);
        onCreate(sqLiteDatabase);
    }

    private void createEmptyTables(SQLiteDatabase database) {
        Log.d("KG", "createEmptyTables вызвался");
        database.execSQL("create table NOTES(id integer primary key, note text)");
        Log.d("KG", "database.execSQL сделали");
    }

    private void deleteTables(SQLiteDatabase database) {
        database.execSQL("DROP TABLE IF EXISTS NOTES");
    }
}
