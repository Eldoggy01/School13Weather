package com.example.admin.school9databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DbHelper dbHelper;

    public DBManager(Context context) {
        Log.d("KG", "Зашли в конструктор DBManager");
        this.dbHelper = new DbHelper(context);
    }

    public void upgradeDB(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade( db, db.getVersion(), db.getVersion()+1);
    }

    public void addNote(String note) {
        SQLiteDatabase db = null;
        try {
            Log.d("KG", "Щас вызовем dbHelper.getWritableDatabase()");
            db = dbHelper.getWritableDatabase();
            Log.d("KG", "После вызова");
            ContentValues contentValues = getContentValue(note);
            db.beginTransaction();
            addNOTESInternal(db, contentValues);
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

    public List getNotes() {
        ArrayList notes = null;
        SQLiteDatabase db = null;
        try {
            Log.d("KG", "Щас вызовем dbHelper.getReadableDatabase()");
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTES", null, null, null, null, null, null);
            notes = parseCursor(cursor);
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


    private ContentValues getContentValue(String notes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("note", notes);
        return contentValues;
    }

    private void addNOTESInternal(SQLiteDatabase db, ContentValues contentValues) {
        db.insert("NOTES", null, contentValues);
    }

    private ArrayList parseCursor(Cursor cursor) {
        ArrayList notes = new ArrayList();
        while (cursor.moveToNext()) {
            notes.add(cursor.getString(cursor.getColumnIndex("note")));
        }
        return notes;
    }
}
