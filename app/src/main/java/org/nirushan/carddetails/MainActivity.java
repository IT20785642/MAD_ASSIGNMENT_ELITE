package org.nirushan.carddetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.nirushan.carddetails.adapter.CardDetailsAdapter;
import org.nirushan.carddetails.databinding.ActivityMainBinding;
import org.nirushan.carddetails.model.Card;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CardDetailsAdapter adapter;
    private ActivityMainBinding binding;
    private List<Card> cardList;
    private DBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DBHandler(getApplicationContext());
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cardList = new ArrayList<>();


        binding.createCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddUpdatePayment.class);
                intent.putExtra("isInsert", true);
                intent.putExtra("cardId", 0);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (cardList.size() > 0){
            cardList.clear();
        }
        cardList.addAll(dbHandler.getAllCard());
        setCardDetails();
    }

    private void setCardDetails(){
        adapter = new CardDetailsAdapter(MainActivity.this, cardList);

        binding.cardDetails.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cardDetails.setItemAnimator(new DefaultItemAnimator());

        LinearLayoutManager leaveStatusRecyclerView = new LinearLayoutManager(this);
        binding.cardDetails.setAdapter(adapter);
        binding.cardDetails.setLayoutManager(leaveStatusRecyclerView);
        leaveStatusRecyclerView.setOrientation(LinearLayoutManager.VERTICAL);
        adapter.notifyDataSetChanged();
    }

}