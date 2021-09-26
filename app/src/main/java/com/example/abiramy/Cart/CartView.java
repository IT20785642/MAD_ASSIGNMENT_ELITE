package com.example.abiramy.Cart;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abiramy.R;


import java.util.ArrayList;

public class CartView extends AppCompatActivity  {
    DatabaseHelper databaseHelper;
    ArrayList<Cart> cartList;
    ListView listView;
    Cart cart;
    Cursor data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_view);
        this.setTitle("Orders");

        databaseHelper = new DatabaseHelper(this);
        cartList = new ArrayList<>();
        listView = findViewById(R.id.cartView);
        data=databaseHelper.displayCart();
        int numRows = data.getCount();

        if(numRows == 0){
            Toast.makeText(CartView.this, "No Details Display!", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                cart = new Cart(data.getString(1),data.getString(2),data.getInt(3),data.getString(4));
                cart.setCartId(data.getInt(0));
                cartList.add(cart);
            }
            ArrayAdapter custom_obj = new CartViewAdapter(this, cartList);
            listView.setAdapter(custom_obj);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View vie, int i, long l) {
                Cart cart = (Cart) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(CartView.this, CartUpdate.class);
                intent.putExtra("Cart", (Parcelable) cart);
                startActivity(intent);
            }

        });

    }


    public void setonClick(View view) {
        Intent intent = new Intent(this,AddCart.class);
        startActivity(intent);
    }


}
