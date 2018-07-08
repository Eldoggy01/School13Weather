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
    private DbHelper mDbHelper;

    public DBManager(Context context) {
        this.mDbHelper = new DbHelper(context);
    }

    public void upgradeDB() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
    }

    public void addNote(String note) {
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getWritableDatabase();
            ContentValues contentValues = getNoteContentValues(null, note);
            db.beginTransaction();
            addContentToDB(db,"NOTES", contentValues);
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


    public void updateNoteInDB(String id, String note) {
        SQLiteDatabase database;
        ContentValues contentValues = getNoteContentValues(id, note);
        database = mDbHelper.getWritableDatabase();
        database.update("NOTES", contentValues, "id=?", new String[]{id});
        database.close();
    }

    public void updateStyleInDB(String id, String color) {
        SQLiteDatabase database;
        ContentValues contentValues = getStyleContentValues(id, color);
        database = mDbHelper.getWritableDatabase();
        database.update("STYLES", contentValues, "id=?", new String[]{id});
        database.close();
    }


    public List<String[]> getNotes() {
        List notes = null;
        SQLiteDatabase db = null;
        try {
            db = mDbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTES", null, null, null, null, null, "id");
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


    private ContentValues getNoteContentValues(String id, String note) {
        ContentValues contentValues = new ContentValues();
        if (id != null) {
            contentValues.put("id", id);
        }
        if (note != null) {
            contentValues.put("note", note);
        }
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
        if (table.toUpperCase().equals("NOTES")) {
            db.insert("NOTES", null, contentValues);
        }
        if (table.toUpperCase().equals("STYLES")) {
            db.insert("STYLES", null, contentValues);
        }
    }


    private List parseNotesCursor(Cursor cursor) {
        List<String[]> notes = new ArrayList();
        while (cursor.moveToNext()) {
            String[] array = new String[2];
            array[0] = cursor.getString(cursor.getColumnIndex("id"));
            array[1] = cursor.getString(cursor.getColumnIndex("note"));
            notes.add(array);
        }
        return notes;
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
