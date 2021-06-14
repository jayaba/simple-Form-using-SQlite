package com.example.tute5new;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "Tute5Db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table UserDetail(id INTEGER primary key ,name TEXT ,address TEXT,age INTEGER,position TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists UserDetail");
    }

    public boolean insertData(int id, String name, String address, int age, String position) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("Name ", name);
        contentValues.put("Address", address);
        contentValues.put("Age", age);
        contentValues.put("Position", position);
        long result = DB.insert("UserDetail", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean DeleteData(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from UserDetail where id=?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("UserDetail", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }


        } else {
            return false;
        }
    }


    public boolean updateData(int id, String name, String address, int age, String position) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("Name ", name);
        contentValues.put("Address", address);
        contentValues.put("Age", age);
        contentValues.put("Position", position);
        Cursor cursor = DB.rawQuery("select * from UserDetail where name=?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("UserDetail",contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }


        } else {
            return false;
        }
    }


    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();

        Cursor cursor = DB.rawQuery("select * from UserDetail",null);
        return  cursor;

    }


}

