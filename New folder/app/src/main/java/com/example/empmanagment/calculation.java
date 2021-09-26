package com.example.empmanagment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calculation extends AppCompatActivity {


    TextView Salary,OTP,Bonus,Tax,Total;
    Button CalButton;

    String Tot;

//    int Salarys = 0;
    int OTPs=0,Bonuss=0,Taxs=0,Totals=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);

        Salary = findViewById(R.id.salAmount);
        OTP = findViewById(R.id.otpAmount);
        Bonus = findViewById(R.id.bonusAmount);
        Tax = findViewById(R.id.taxAmount);
        Total = findViewById(R.id.totalAmount);

        CalButton = findViewById(R.id.cal_btn);


        CalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Tot = Salary.getText().toString();
                 int Salarys = Integer.parseInt(Salary.getText().toString());
                OTPs = Integer.parseInt(OTP.getText().toString());
                Bonuss = Integer.parseInt(Bonus.getText().toString());
                Taxs = Integer.parseInt(Tax.getText().toString());

                Totals =Salarys +OTPs;

                //Toast.makeText(calculation.this,Salarys, Toast.LENGTH_SHORT).show();
                Toast.makeText(calculation.this, Salarys, Toast.LENGTH_SHORT).show();

//                Total.setText(Totals);


            }
        });

    }
}