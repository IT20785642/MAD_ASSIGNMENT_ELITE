package org.nirushan.carddetails;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.nirushan.carddetails.model.Card;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddUpdatePayment extends AppCompatActivity {

    private EditText numberOfItems;
    private EditText subTotal;
    private EditText serviceCharge;
    private EditText total;
    private DBHandler dbHandler;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Activity activity;

    private String cardHolderValue = "";
    private String cardNumberValue = "";
    private String cvNumberValue = "";
    private String expiryValue = "";
    private boolean isInsert =  true;
    private int cardId = 0;

    private void clear(){
        cardHolderValue = "";
        cardNumberValue = "";
        cvNumberValue = "";
        expiryValue = "";
        numberOfItems.setText("1");
        subTotal.setText("");
        serviceCharge.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_payment);
        activity = AddUpdatePayment.this;
        Intent intent = getIntent();
        if (intent != null){
            isInsert = intent.getExtras().getBoolean("isInsert");
            cardId = intent.getExtras().getInt("cardId");
        }
        dbHandler = new DBHandler(getApplicationContext());
        TextView itemDetails = findViewById(R.id.itemDetails);
        numberOfItems = findViewById(R.id.customerNameView);
        subTotal = findViewById(R.id.subTotal);
        serviceCharge = findViewById(R.id.serviceCharge);
        //total = findViewById(R.id.total);
        Button conformButton = findViewById(R.id.conformButton);
        Button cancelButton = findViewById(R.id.cancelButton);
        radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.cashRadio:

                    break;
                case R.id.cardRadio:
                    addCardDetails();
                    break;
            }
        });

        if (!isInsert){
            List<Card> list = dbHandler.getCardDetailsById(cardId);
            if (list.size()>0){
                Card card = list.get(0);
                setData(card);
            }
        }

        conformButton.setText(isInsert ? "Create" : "Update");

        conformButton.setOnClickListener(v -> {

            if (validateValues(numberOfItems) && validateValues(subTotal) && validateValues(serviceCharge)){
                double totalVal = (validateDouble(subTotal)*validateDouble(numberOfItems))+validateDouble(serviceCharge);
                AlertDialog.Builder builder1 = new AlertDialog.Builder(activity);
                builder1.setMessage("Order Total is "+totalVal +" press Yes to conform");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Card card = new Card();
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.US);
                                String time = df.format(new Date());

                                int selectedId = radioGroup.getCheckedRadioButtonId();
                                radioButton = findViewById(selectedId);

                                card.setCreated(time);

                                card.setServiceCharge(validateDouble(serviceCharge));
                                card.setTotal(validateDouble(subTotal));
                                card.setQty(validateDouble(numberOfItems));
                                card.setPaymentStatus(radioButton.getText().toString());

                                card.setCARD_CUSTOMER_ID("0");
                                card.setCARD_OWNER(cardHolderValue);
                                card.setCV_CODE(cvNumberValue);
                                card.setEXPIRY_DATE(expiryValue);
                                card.setCARD_NUMBER(cardNumberValue);

                                if (isInsert){
                                    dbHandler.addCard(card);
                                }else {
                                    card.setCardId(cardId);
                                    dbHandler.updateCard(card);
                                    finish();
                                }

                                Toast.makeText(getApplicationContext(), "Created.", Toast.LENGTH_SHORT).show();
                                clear();
                                dialog.cancel();
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



            }else{
                Toast.makeText(getApplicationContext(), "Required Field!", Toast.LENGTH_SHORT).show();
            }

        });

        cancelButton.setOnClickListener(v -> finish());
    }
    private void setData(Card card){
        cardHolderValue = card.getCARD_OWNER();
        cardNumberValue = card.getCARD_NUMBER();
        cvNumberValue = card.getCV_CODE();
        expiryValue = card.getEXPIRY_DATE();
        numberOfItems.setText(String.valueOf(card.getQty()));
        subTotal.setText(String.valueOf(card.getTotal()));
        serviceCharge.setText(String.valueOf(card.getServiceCharge()));
        if (card.getPaymentStatus().equalsIgnoreCase("Cash")){
            ((RadioButton)radioGroup.getChildAt(0)).setChecked(true);
        }else{
            ((RadioButton)radioGroup.getChildAt(1)).setChecked(true);
        }

    }
    private void addCardDetails() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.card_details_view, null);
        dialogBuilder.setView(dialogView);

        EditText cardHolder = dialogView.findViewById(R.id.cardHolder);
        EditText cardNumber = dialogView.findViewById(R.id.cardNumber);
        EditText cvNumber = dialogView.findViewById(R.id.cvNumber);
        EditText expiry = dialogView.findViewById(R.id.expiry);

        if (!isInsert){
            cardHolder.setText(cardHolderValue);
            cardNumber.setText(cardNumberValue);
            cvNumber.setText(cvNumberValue);
            expiry.setText(expiryValue);
        }

        Button add = dialogView.findViewById(R.id.add);
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();


        add.setOnClickListener(v -> {
            if (validateValues(cardHolder) && validateValues(cardNumber) && validateValues(cvNumber) && validateValues(expiry)){
                if (cardNumber.getText().toString().length() == 16){
                    cardHolderValue = cardHolder.getText().toString().trim();
                    cardNumberValue = cardNumber.getText().toString().trim();
                    cvNumberValue = cvNumber.getText().toString().trim();
                    expiryValue = expiry.getText().toString().trim();
                    alertDialog.dismiss();
                }else {
                    cardNumber.setError("Card Number length miss match!");
                }

            }else{
                Toast.makeText(getApplicationContext(), "Required Card Field!", Toast.LENGTH_SHORT).show();
            }


        });
    }
    private double validateDouble(EditText editText){
        return Double.parseDouble(editText.getText().toString());
    }
    private boolean validateValues(EditText editText){
        return editText.getText().toString().trim().length() > 0;
    }
}