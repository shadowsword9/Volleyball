package com.example.share;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "share.db";
    public static final String TABLE_NAME = "share_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "STORY";
    public static final String COL_3 = "LIKED";


    public DatabaseHelper(Context context) { //when this constructor called db will be made
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); //this will create db and table
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, STORY TEXT, LIKED INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
