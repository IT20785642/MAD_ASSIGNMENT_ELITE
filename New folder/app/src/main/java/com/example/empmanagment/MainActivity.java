package com.example.empmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empmanagment.Database.*;


public class MainActivity extends AppCompatActivity {


    EditText Name;
    EditText Email;
    EditText Department;
    EditText Branch;
    EditText NIC;
    EditText Contact;

    Button AddData;

    String Names;
    String Emails;
    String Departments;
    String Branchs;
    String NICs;
    String Contacts;

     DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Name = findViewById(R.id.editName);
        Email = findViewById(R.id.editEmail);
        Department = findViewById(R.id.editDepartment);
        Branch = findViewById(R.id.editBranch);
        NIC = findViewById(R.id.editNic);
        Contact = findViewById(R.id.editcontact);
        AddData = findViewById(R.id.addnew_button);

        DB = new DBHelper(this);

        AddData.setOnClickListener(v -> {
            Names = Name.getText().toString();
            Emails = Email.getText().toString();
            Departments = Department.getText().toString();
            Branchs = Branch.getText().toString();
            NICs = NIC.getText().toString();
            Contacts = Contact.getText().toString();

            Toast.makeText(MainActivity.this,Names, Toast.LENGTH_SHORT).show();

            SaveToLocalDB(Names,Emails,Departments,Branchs,NICs,Contacts);

        });


    }

    public void SaveToLocalDB(String Name, String Email, String Department, String Branch, String NIC, String Contact){
        SQLiteDatabase database = DB.getWritableDatabase();
        DB.StoreUserDetails(Name, Email, Department,Branch,NIC,Contact);
//        Toast.makeText(MainActivity.this,"Data Inserted", Toast.LENGTH_SHORT).show();

    }

}