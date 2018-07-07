package com.example.admin.school9databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private DbHelper dbHelper;

    public DBManager(Context context) {
        Log.d("KG", "Зашли в конструктор DBManager");
        this.dbHelper = new DbHelper(context);
    }

    public void upgradeDB() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.onUpgrade(db, db.getVersion(), db.getVersion() + 1);
    }

    public void addNote(String note) {
        SQLiteDatabase db = null;
        try {
            Log.d("KG", "Щас вызовем dbHelper.getWritableDatabase()");
            db = dbHelper.getWritableDatabase();
            Log.d("KG", "После вызова");
            ContentValues contentValues = getContentValues(null, note);
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


    public void updateById(String id, String note) {
        Log.d("KG", "Зашли в updateById и пытаемся вставить id = "+id+"и note = "+note);
        SQLiteDatabase database;
        ContentValues contentValues = getContentValues(id, note);
        database = dbHelper.getWritableDatabase();
        database.update("NOTES", contentValues,"id=?", new String[]{id});
        List<String[]> notes = getNotes();
        for (String[] s : notes) {
            Log.d("KG", "Выводим  массив: id = "+ s[0] + "note = "+s[1] );
        }
        database.close();
    }


//    public String[] getDBlineById(String id) {
//        Cursor cursor = null;
//        String[] stringArray = new String[2];
//        SQLiteDatabase database = null;
//
//            database = dbHelper.getReadableDatabase();
//            cursor = database.rawQuery("SELECT * FROM NOTES WHERE id=?", new String[]{id});
//            while (cursor.moveToNext()) {
//                stringArray[0] = cursor.getString(cursor.getColumnIndex("id"));
//                System.out.println(stringArray[0]);
//                stringArray[1] = cursor.getString(cursor.getColumnIndex("note"));
//            }
//
//            return stringArray;
//
//    }

    public List<String[]> getNotes() {
        List notes = null;
        SQLiteDatabase db = null;
        try {
            Log.d("KG", "Щас вызовем dbHelper.getReadableDatabase()");
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTES", null, null, null, null, null, "id");
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


    private ContentValues getContentValues(String id, String note) {
        ContentValues contentValues = new ContentValues();
        if (id != null) {
            contentValues.put("id", id);
        }
        if (note != null) {
            contentValues.put("note", note);
        }
        return contentValues;
    }

    private void addNOTESInternal(SQLiteDatabase db, ContentValues contentValues) {
        db.insert("NOTES", null, contentValues);
    }

    private List parseCursor(Cursor cursor) {
        List<String[]> notes = new ArrayList();
        while (cursor.moveToNext()) {
            String[] array = new String[2];
            array[0] = cursor.getString(cursor.getColumnIndex("id"));
            array[1] = cursor.getString(cursor.getColumnIndex("note"));
            notes.add(array);
        }
        return notes;
    }
}
