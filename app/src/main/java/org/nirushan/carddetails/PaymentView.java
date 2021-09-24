package org.nirushan.carddetails;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.nirushan.carddetails.model.Card;

import java.util.List;

public class PaymentView extends AppCompatActivity {

    private TextView customerNameView;
    private TextView itemNameView;
    private TextView unitPrice;
    private TextView qtyView;
    private TextView totalVIew;
    private TextView serviceChargeView;
    private TextView cardNumberView;
    private Button editPayment;
    private Button deletePayment;
    private DBHandler dbHandler;
    private RelativeLayout customerViewRelative;
    private RelativeLayout cardNumberViewRelative;
    private int card_id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_by_id);

        dbHandler = new DBHandler(getApplicationContext());
        Intent intent = getIntent();
        if (intent != null){
            card_id = intent.getExtras().getInt("card_id");
        }
        customerNameView = findViewById(R.id.customerNameView);
        itemNameView = findViewById(R.id.itemNameView);
        unitPrice = findViewById(R.id.unitPrice);
        qtyView = findViewById(R.id.qtyView);
        totalVIew = findViewById(R.id.totalVIew);
        serviceChargeView = findViewById(R.id.serviceChargeView);
        customerViewRelative = findViewById(R.id.customerViewRelative);
        editPayment = findViewById(R.id.editPayment);
        deletePayment = findViewById(R.id.deletePayment);
        cardNumberView = findViewById(R.id.cardNumberView);
        cardNumberViewRelative = findViewById(R.id.cardNumberViewRelative);
        itemNameView.setText("Pizza");

        editPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddUpdatePayment.class);
                intent.putExtra("isInsert", false);
                intent.putExtra("cardId", card_id);
                startActivity(intent);
            }
        });

        deletePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(PaymentView.this);
                builder1.setMessage("Are you sure do you want delete?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dbHandler.deleteCard(card_id);
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                finish();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Card> list = dbHandler.getCardDetailsById(card_id);
        if (list.size()>0){
            Card card = list.get(0);
            setData(card);
        }
    }

    private void setData(Card card){
        if (card.getCARD_OWNER().equalsIgnoreCase("")){
            customerViewRelative.setVisibility(View.GONE);
            cardNumberViewRelative.setVisibility(View.GONE);
        }else{
            customerNameView.setText(card.getCARD_OWNER());
            String letter = card.getCARD_NUMBER().substring(12,16);
            cardNumberView.setText("**** **** **** "+letter);
        }
        unitPrice.setText(String.valueOf(card.getTotal()));
        serviceChargeView.setText(String.valueOf(card.getServiceCharge()));
        qtyView.setText(String.valueOf(card.getQty()));

        double totalVal = (card.getTotal()*card.getQty())+card.getServiceCharge();
        totalVIew.setText(String.valueOf(totalVal));


    }
}