package com.example.abiramy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DatabaseHelper extends Database{
    public DatabaseHelper(@Nullable Context context) {
        super(context);
    }

    public boolean insertUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("U_Name",user.getUserName());
        contentValues.put("U_Email",user.getUserEmail());
        contentValues.put("U_Mobie",user.getUserMobile());
        contentValues.put("U_Address",user.getUserAddress());

        SQLiteDatabase db= getWritableDatabase();
        long result=db.insert("User",null,contentValues);

        return result != -1;

    }
    public boolean updateUser(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("U_Name",user.getUserName());
        contentValues.put("U_Email",user.getUserEmail());
        contentValues.put("U_Mobie",user.getUserMobile());
        contentValues.put("U_Address",user.getUserAddress());

        SQLiteDatabase db= getReadableDatabase();
        int result=db.update("User",contentValues,"U_ID ="+user.getUserId(),null);

        return result != -1;

    }

    public Cursor displayUser(){
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM User";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }
    public boolean deleteUser(int id){
        SQLiteDatabase db= getReadableDatabase();

        int result=db.delete("User","U_ID ="+id,null);
        return result != -1;
    }
}
