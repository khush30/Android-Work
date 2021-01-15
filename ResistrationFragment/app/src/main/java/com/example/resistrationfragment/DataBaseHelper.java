package com.example.resistrationfragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.resistrationfragment.ProfilePojo;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, "UserDataBase.db" , null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table users (name text , contactNumber text , email text primary key , gender text , district text , dob  , password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists users");

    }

    public Boolean insertInUsers(ProfilePojo user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , user.username);
        contentValues.put("contactNumber" , user.phoneNumber);
        contentValues.put("email" , user.emailId);
        contentValues.put("gender" , user.gender);
        contentValues.put("district" , user.district);
        contentValues.put("dob" , user.date);
        contentValues.put("password" , user.password);

        long result = db.insert("users" , null , contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Boolean updateInUsers(ProfilePojo user) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , user.username);
        contentValues.put("contactNumber" , user.phoneNumber);
        contentValues.put("gender" , user.gender);
        contentValues.put("state" , "Goa");
        contentValues.put("district" , user.district);
        contentValues.put("dob" , user.date);
        contentValues.put("password" , user.password);

        Cursor cursor = db.rawQuery("select * from users where email=?" , new String[]{user.emailId});

        if(cursor.getCount() > 0) {
            long result = db.update("users", contentValues, "email=?", new String[]{user.emailId});

            if (result == -1)
                return false;
            else
                return true;
        }
        else {
            return false;
        }

    }

    public Cursor getUsersData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users" , null);
        return cursor;

    }

}
