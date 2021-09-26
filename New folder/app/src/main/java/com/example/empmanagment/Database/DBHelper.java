package com.example.empmanagment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.empmanagment.EmpModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "employeemanagment.db";
    private static final String TABLE1_NAME = "employer";

    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_Email = "Email";
    private static final String COLUMN_Department = "Department";
    private static final String COLUMN_Branch = "Branch";
    private static final String COLUMN_NIC = "NIC";
    private static final String COLUMN_Contact = "Contact";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            String TABLE_Empolyee = "CREATE TABLE " + TABLE1_NAME + "("
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_Email + " TEXT NOT NULL,"
                    + COLUMN_Department + " TEXT NOT NULL,"
                    + COLUMN_Branch + " TEXT NOT NULL,"
                    + COLUMN_NIC + " TEXT NOT NULL,"
                    + COLUMN_Contact + " TEXT NOT NULL )";

            sqLiteDatabase.execSQL(TABLE_Empolyee);
        } catch (Exception e) {
            Log.d("1", e.toString());
        }

        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);

        if(newVersion >oldVersion) {
            onCreate(sqLiteDatabase);
        }
    }

    public void StoreUserDetails(String Name, String Email, String Department, String Branch, String NIC, String Contact){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, Name);
        contentValues.put(COLUMN_Email, Email);
        contentValues.put(COLUMN_Department, Department);
        contentValues.put(COLUMN_Branch, Branch);
        contentValues.put(COLUMN_NIC, NIC);
        contentValues.put(COLUMN_Contact, Contact);

        database.insert(TABLE1_NAME,null,contentValues);
    }

    public ArrayList<EmpModel> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE1_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<EmpModel> empArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                empArrayList.add(new EmpModel(
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(2),
                        cursorCourses.getString(2),
                        cursorCourses.getString(2),
                        cursorCourses.getString(2)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return empArrayList;
    }
}
