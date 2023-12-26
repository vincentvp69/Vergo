package com.example.vergo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";
    public static final String settingsTable = "settings";
    public static final String colUsername = "username";
    public static final String colFavMeal = "favMeal";
    public static final String colFavLocation = "favLocation";
    public static final String colNotification = "notification";

    public DatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 2); // Increment the version number
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(email TEXT primary key, password TEXT)");
        MyDatabase.execSQL("create Table " + settingsTable + " (" +
                "email TEXT primary key, " +  // Make sure 'email' is the primary key
                colUsername + " TEXT, " +
                colFavMeal + " TEXT, " +
                colFavLocation + " TEXT, " +
                colNotification + " INTEGER)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop Table if exists allusers");
        MyDatabase.execSQL("drop Table if exists " + settingsTable);
        onCreate(MyDatabase);
    }
    public Boolean insertData(String email, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("allusers", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ?", new String[]{email});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }



    // Insert user settings into the settings table
    public boolean insertSettings(String email, String username, String favMeal, String favLocation, int notification) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);  // Add this line
        contentValues.put(colUsername, username);
        contentValues.put(colFavMeal, favMeal);
        contentValues.put(colFavLocation, favLocation);
        contentValues.put(colNotification, notification);

        long result = MyDatabase.insert(settingsTable, null, contentValues);

        return result != -1;
    }


    // Retrieve user settings from the settings table
    public Cursor loadSettings(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        String[] columns = {colUsername, colFavMeal, colFavLocation, colNotification};
        String selection = "email=?";
        String[] selectionArgs = {email};
        Cursor cursor = MyDatabase.query(settingsTable, columns, selection, selectionArgs, null, null, null);

        return cursor;
    }
}
