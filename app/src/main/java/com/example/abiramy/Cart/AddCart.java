package com.example.abiramy.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.abiramy.MainActivity;
import com.example.abiramy.R;

public class AddCart extends AppCompatActivity {
    Spinner item,size;
    EditText order,desc;
    Button btn_add;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addto_cart);

        item=findViewById(R.id.spinner1);
        size = findViewById(R.id.spinner2);
        order = findViewById(R.id.ord);
        desc = findViewById(R.id.desc);
        btn_add = findViewById(R.id.btn_add);
        databaseHelper = new DatabaseHelper(this);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!item.getSelectedItem().toString().isEmpty()) {
                        if (!size.getSelectedItem().toString().isEmpty()) {
                            if (!order.getText().toString().isEmpty()) {
                                if (!desc.getText().toString().isEmpty()) {

                                    String item_name = item.getSelectedItem().toString();
                                    String item_size = size.getSelectedItem().toString();
                                    if(!item_name.equals("Please select item")){
                                        if(!item_size.equals("Please select size")){
                                            int item_order = Integer.parseInt(order.getText().toString());
                                            String item_desc = desc.getText().toString();

                                            Cart cart = new Cart(item_name,item_size,item_order,item_desc);
                                            addData(cart);
                                            
                                            
                                        }else
                                            Toast.makeText(AddCart.this, "Size should be Selected!!!", Toast.LENGTH_LONG).show();
                                    }else
                                        Toast.makeText(AddCart.this, "Item Name should be Selected!!!", Toast.LENGTH_LONG).show();

                                }else
                                    Toast.makeText(AddCart.this, "Description cannot be empty!!!", Toast.LENGTH_LONG).show();

                            }else
                                Toast.makeText(AddCart.this, "Number of Orders cannot be empty!!!", Toast.LENGTH_LONG).show();

                        }else
                            Toast.makeText(AddCart.this, "Size should be Selected!!!", Toast.LENGTH_LONG).show();
                    }else
                        Toast.makeText(AddCart.this, "Item Name should be Selected!!!", Toast.LENGTH_LONG).show();

                }catch (Exception e) {
                    Toast.makeText(AddCart.this, "Invalid Numeric Input!!!", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void addData(Cart cart) {
        if (databaseHelper.insertCart(cart)) {
            Toast.makeText(AddCart.this, "Successfully Inserted!!!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AddCart.this, CartView.class);
            startActivity(intent);
        } else
            Toast.makeText(AddCart.this, "Error while Inserting....", Toast.LENGTH_LONG).show();
    }

}