package com.example.abiramy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    private static final String DATABASE_NAME = "pizza.db";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table User(U_ID INTEGER PRIMARY KEY AUTOINCREMENT, U_Name TEXT, U_Email TEXT, U_Mobie INTEGER,U_Address TEXT);");
        db.execSQL("Create Table Cart(C_ID INTEGER PRIMARY KEY AUTOINCREMENT, I_Name TEXT, I_Size TEXT, No_Order INTEGER,Description TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS Cart");

    }
}
