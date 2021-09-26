package com.example.abiramy;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UserUpdate extends AppCompatActivity {
    EditText fname , email,mobile,address;
    ProgressBar progressBar;
    Button btnUpdate,btnDelete;
    DatabaseHelper databaseHelper;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);


        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        user = intent.getParcelableExtra("User");

        fname = findViewById(R.id.fname);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        address = findViewById(R.id.address);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);


       fname.setText(user.getUserName());
        email.setText(user.getUserEmail());
        mobile.setText(Integer.toString(user.getUserMobile()));
        address.setText(user.getUserAddress());

        fname.setEnabled(false);
        email.setEnabled(false);
        mobile.setEnabled(false);
        address.setEnabled(false);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View view) {
                                             if (btnUpdate.getText().toString().toUpperCase().equals("EDIT")) {
                                                 btnUpdate.setText("UPDATE");
                                                 fname.setEnabled(true);
                                                 email.setEnabled(true);
                                                 mobile.setEnabled(true);
                                                 address.setEnabled(true);
                                             }
                                                 else{
                                                     try {
                                                         if (!fname.getText().toString().isEmpty()) {
                                                             if (!email.getText().toString().isEmpty()) {
                                                                 if (!mobile.getText().toString().isEmpty()) {
                                                                     if (!address.getText().toString().isEmpty()) {

                                                                         btnUpdate.setVisibility(View.INVISIBLE);
                                                                         btnDelete.setVisibility(View.INVISIBLE);
                                                                         progressBar.setVisibility(View.VISIBLE);

                                                                         String user_name = fname.getText().toString();
                                                                         String user_email = email.getText().toString();
                                                                         int user_mobile = Integer.parseInt(mobile.getText().toString());
                                                                         String user_address = address.getText().toString();

                                                                         User user1 = new User(user_name, user_email, user_mobile, user_address);
                                                                         user1.setUserId(user.getUserId());
                                                                         updateData(user1);

                                                                     } else
                                                                         Toast.makeText(UserUpdate.this, "Address cannot be empty!!!", Toast.LENGTH_LONG).show();
                                                                 } else
                                                                     Toast.makeText(UserUpdate.this, "Mobile cannot be empty!!!", Toast.LENGTH_LONG).show();

                                                             } else
                                                                 Toast.makeText(UserUpdate.this, "Email cannot be empty!!!", Toast.LENGTH_LONG).show();
                                                         } else
                                                             Toast.makeText(UserUpdate.this, "Name cannot be empty!!!", Toast.LENGTH_LONG).show();

                                                     } catch (NumberFormatException ex) {
                                                         Toast.makeText(UserUpdate.this, "Invalid Inputs", Toast.LENGTH_LONG).show();
                                                     }
                                                 }
                                             }

                                     }
            );


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnUpdate.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                databaseHelper.deleteUser(user.getUserId());

                fname.setText("");
                email.setText("");
                mobile.setText("");
                address.setText("");

                Toast.makeText(UserUpdate.this, "User Successfully Deleted!!!", Toast.LENGTH_LONG).show();

                Intent intent1 = new Intent(UserUpdate.this, Register.class);
                startActivity(intent1);
            }
            });

    }
        public void updateData(User user){
        if(databaseHelper.updateUser(user)){
            Toast.makeText(UserUpdate.this,"Successfully Updated!!!",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(UserUpdate.this,UserView.class);
            startActivity(intent);
        }else
            Toast.makeText(UserUpdate.this, "Error Occurred..........", Toast.LENGTH_LONG).show();
        }
}
