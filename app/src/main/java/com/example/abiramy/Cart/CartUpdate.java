package com.example.abiramy.Cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abiramy.R;

import androidx.appcompat.app.AppCompatActivity;

public class CartUpdate extends AppCompatActivity {
    Spinner item,size;
    EditText order,desc;
    DatabaseHelper databaseHelper;
    ProgressBar progressBar;
    Button btnUpdate,btnDelete;
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_update);

        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        cart = intent.getParcelableExtra("Cart");

        item = findViewById(R.id.spinner1);
        size = findViewById(R.id.spinner2);
        order = findViewById(R.id.ord);
        desc = findViewById(R.id.desc);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


        String pizzatype = cart.getC_item();
        String pizzasize = cart.getC_size();
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.item,R.layout.support_simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.size,R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        item.setAdapter(adapter1);
        size.setAdapter(adapter2);
        if((pizzatype!=null) && (pizzasize!=null)){
            int spinnerPos1=adapter1.getPosition(pizzatype);
            item.setSelection(spinnerPos1);
            int spinnerPos2=adapter2.getPosition(pizzasize);
            size.setSelection(spinnerPos2);

        }
        order.setText(Integer.toString(cart.getC_no_order()));
        desc.setText(cart.getC_desc());

        item.setEnabled(false);
        size.setEnabled(false);
        order.setEnabled(false);
        desc.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnUpdate.getText().toString().toUpperCase().equals("EDIT")) {
                    btnUpdate.setText("UPDATE");
                    item.setEnabled(true);
                    size.setEnabled(true);
                    order.setEnabled(true);
                    desc.setEnabled(true);

                } else {

                    try {
                        if (!item.getSelectedItem().toString().equals("Please select item")) {
                            if (!size.getSelectedItem().toString().equals("Please select size")) {
                                if (!order.getText().toString().isEmpty()) {
                                    if (!desc.getText().toString().isEmpty()) {

                                        btnUpdate.setVisibility(View.INVISIBLE);
                                        btnDelete.setVisibility(View.INVISIBLE);
                                        progressBar.setVisibility(View.VISIBLE);

                                        String pizza_type = item.getSelectedItem().toString();
                                        String pizza_size = size.getSelectedItem().toString();
                                        int pizza_order = Integer.parseInt(order.getText().toString());
                                        String pizza_desc = desc.getText().toString();

                                        Cart cart1 = new Cart(pizza_type, pizza_size, pizza_order, pizza_desc);
                                        cart1.setCartId(cart.getCartId());
                                        updateData(cart1);
                                    } else
                                        Toast.makeText(CartUpdate.this, "Description cannot be empty!!!", Toast.LENGTH_LONG).show();

                                } else
                                    Toast.makeText(CartUpdate.this, "Number of Orders cannot be empty!!!", Toast.LENGTH_LONG).show();

                            } else
                                Toast.makeText(CartUpdate.this, "Size should be Selected!!!", Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(CartUpdate.this, "Item Name should be Selected!!!", Toast.LENGTH_LONG).show();

                    } catch (Exception e) {
                        Toast.makeText(CartUpdate.this, "Invalid Numeric Input!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }

        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUpdate.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                databaseHelper.deleteCart(cart.getCartId());

                order.setText("");
                desc.setText("");


                Toast.makeText(CartUpdate.this,"Cart Successfully Deleted!!!",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(CartUpdate.this,CartView.class);
                startActivity(intent);
            }
        });
    }
    public void updateData(Cart cart){

        if(databaseHelper.updateCart(cart)){
            Toast.makeText(CartUpdate.this,"Successfully Updated!!!",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(CartUpdate.this,CartView.class);
            startActivity(intent);
        }

        else
            Toast.makeText(CartUpdate.this,"Error while Updating....",Toast.LENGTH_LONG).show();
    }
}
