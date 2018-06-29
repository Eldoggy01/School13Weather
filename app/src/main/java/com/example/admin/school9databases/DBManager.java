package com.example.admin.school9databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DbHelper dbHelper;

    public DBManager(Context context){
        this.dbHelper = new DbHelper(context);
    }

    public void addNote(String note){
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getWritableDatabase();
            ContentValues contentValues = getContentValues(note);
            db.beginTransaction();
            addNotesInternal(db,contentValues);
            db.setTransactionSuccessful();

        }catch(SQLException e){

        }finally{
            if(db!=null){
                if (db.inTransaction()){
                    db.endTransaction();
                }
                db.close();
            }
        }
    }

    public String getNote(){
        String note = null;
        SQLiteDatabase db = null;
        try{
            db = dbHelper.getReadableDatabase();
            db.beginTransaction();
            Cursor cursor = db.query("NOTES",null,null,null,null,null,null);
            note = parseCursor(cursor);
            cursor.close();
            db.setTransactionSuccessful();

        }catch(SQLException e){

        }finally{
            if(db!=null){
                if (db.inTransaction()){
                    db.endTransaction();
                }
                db.close();
            }
        }
        return  note;
    }

    private ContentValues getContentValues(String note){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOTE",note);
        return  contentValues;
    }

    private void addNotesInternal(SQLiteDatabase db, ContentValues contentValues){
        db.insert("NOTES",null,contentValues);
    }

    private String parseCursor(Cursor cursor){
        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex("NOTE"));
        }
        return  null;
    }
}
