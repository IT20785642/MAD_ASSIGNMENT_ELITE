package com.example.abiramy.Cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.abiramy.Database;

public class DatabaseHelper extends Database {

    public DatabaseHelper(@Nullable Context context) {
        super(context);
    }

    public boolean insertCart(Cart cart){
        ContentValues contentValues = new ContentValues();
        contentValues.put("I_Name",cart.getC_item());
        contentValues.put("I_Size", cart.getC_size());
        contentValues.put("No_Order", cart.getC_no_order());
        contentValues.put("Description",cart.getC_desc());

        SQLiteDatabase db= getWritableDatabase();
        long result=db.insert("Cart",null,contentValues);

        return result != -1;
    }

    public boolean updateCart(Cart cart){
        ContentValues contentValues = new ContentValues();
        contentValues.put("I_Name",cart.getC_item());
        contentValues.put("I_Size", cart.getC_size());
        contentValues.put("No_Order", cart.getC_no_order());
        contentValues.put("Description",cart.getC_desc());

        SQLiteDatabase db= getReadableDatabase();
        long result=db.update("Cart",contentValues,"C_ID =" +cart.getCartId(),null);

        return result != -1;

    }

    public Cursor displayCart(){
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM Cart";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;

    }
    public boolean deleteCart(int id){
        SQLiteDatabase db= getReadableDatabase();

        int result=db.delete("Cart","C_ID ="+id,null);
        return result != -1;

    }
}
