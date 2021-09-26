package com.example.abiramy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abiramy.Cart.AddCart;

import java.util.ArrayList;

public class UserView extends AppCompatActivity implements View.OnClickListener{

    DatabaseHelper databaseHelper;
    ArrayList<User> userList;
    ListView listView;
    User user;
    Cursor data;

    private Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        this.setTitle("User Profile");

        add = findViewById(R.id.cart);
        add.setOnClickListener( UserView.this);

        databaseHelper = new DatabaseHelper(this);

        userList = new ArrayList<>();
        listView = findViewById(R.id.userView);
        data=databaseHelper.displayUser();
        int numRows = data.getCount();


        if(numRows == 0){
            Toast.makeText(UserView.this, "No Details Display!", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                user = new User(data.getString(1),data.getString(2),data.getInt(3),data.getString(4));
                user.setUserId(data.getInt(0));
                userList.add(user);
            }
            ArrayAdapter custom_obj = new UserViewAdapter(this, userList);
            listView.setAdapter(custom_obj);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View vie, int i, long l) {
                User user = (User) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(UserView.this, UserUpdate.class);
                intent.putExtra("User", (Parcelable) user);
                startActivity(intent);
            }

        });

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(UserView.this, AddCart.class);
        startActivity(intent);

    }
}
