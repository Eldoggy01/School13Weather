package com.example.admin.school9databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class DBManager {
    private DbHelper dbHelper;

    public DBManager(Context context) {
        Log.d("GG","Зашли в конструктор DBManager");
        this.dbHelper = new DbHelper(context);
    }

    public void addNote(String note) {
        SQLiteDatabase db = null;
        try {
            Log.d("GG","Щас вызовем dbHelper.getWritableDatabase()");
            db = dbHelper.getWritableDatabase();
            Log.d("GG","После вызова");
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

    public String getNote() {
        String note = null;
        SQLiteDatabase db = null;
        try {
            Log.d("GG","Щас вызовем dbHelper.getReadableDatabase()");
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTES", null, null, null, null, null, null);
            note = parseCursor(cursor);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {

        }finally{
            if (db != null) {
                if (db.inTransaction()) {
                    db.endTransaction();
                }
                db.close();
            }
        }
        return note;
    }


    private ContentValues getContentValue(String notes) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTE", notes);
        return contentValues;
    }

    private void addNOTESInternal(SQLiteDatabase db, ContentValues contentValues) {
        db.insert("NOTES", null, contentValues);
    }

    private String parseCursor(Cursor cursor) {
        if (cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex("name"));
        }
        return null;
    }
}
