package com.example.abiramy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

public class Register extends AppCompatActivity {

    EditText rName, rEmail, rMobile, rAddress;
    Button rAdd;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        rName = findViewById(R.id.fname);
        rEmail = findViewById(R.id.email);
        rMobile = findViewById(R.id.mobile);
        rAddress = findViewById(R.id.address);
        rAdd = findViewById(R.id.btn_reg);

        databaseHelper = new DatabaseHelper(this);

        rAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (!rName.getText().toString().isEmpty()) {
                        if (!rEmail.getText().toString().isEmpty()) {
                            if (!rMobile.getText().toString().isEmpty()) {
                                if (!rAddress.getText().toString().isEmpty()) {

                                    String user_name = rName.getText().toString();
                                    String user_email = rEmail.getText().toString();
                                    int user_mobile = Integer.parseInt(rMobile.getText().toString());
                                    String user_address = rAddress.getText().toString();

                                    User user = new User(user_name, user_email, user_mobile, user_address);
                                    addData(user);

                                } else
                                    Toast.makeText(Register.this, "Address cannot be empty!!!", Toast.LENGTH_LONG).show();
                            } else
                                Toast.makeText(Register.this, "Mobile cannot be empty!!!", Toast.LENGTH_LONG).show();

                        } else
                            Toast.makeText(Register.this, "Email cannot be empty!!!", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(Register.this, "Name cannot be empty!!!", Toast.LENGTH_LONG).show();

                } catch (NumberFormatException ex) {
                    Toast.makeText(Register.this, "Invalid Inputs", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
        private void addData(User user){
            if(databaseHelper.insertUser(user)){
                Toast.makeText(Register.this,"Successfully Registered", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Register.this, UserView.class);
                startActivity(intent);
            }
            else
                Toast.makeText(Register.this, "Error while Inserting....", Toast.LENGTH_LONG).show();
        }

}




