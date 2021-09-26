package com.example.empmanagment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.empmanagment.Database.DBHelper;

import java.util.ArrayList;

public class getDetails extends AppCompatActivity {

    private RecyclerView Top_recyclerview;
    private ArrayList<EmpModel> courseModalArrayList;
    private DBHelper dbHandler;
    private EMPAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_details);

        Top_recyclerview = findViewById(R.id.Top_recyclerview);

        courseModalArrayList = new ArrayList<>();
        dbHandler = new DBHelper(getDetails.this);

        courseModalArrayList = dbHandler.readCourses();


        courseRVAdapter = new EMPAdapter(courseModalArrayList, getDetails.this);
//        coursesRV = findViewById(R.id.Top_recyclerview);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getDetails.this, RecyclerView.VERTICAL, false);
        Top_recyclerview.setLayoutManager(linearLayoutManager);

        Top_recyclerview.setAdapter(courseRVAdapter);
    }
}