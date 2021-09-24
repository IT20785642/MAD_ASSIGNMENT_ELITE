package org.nirushan.carddetails.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import org.nirushan.carddetails.PaymentView;
import org.nirushan.carddetails.R;
import org.nirushan.carddetails.model.Card;

import java.util.List;


public class CardDetailsAdapter extends RecyclerView.Adapter<CardDetailsAdapter.viewHolder> {
    Activity activity;
    private List<Card> cardList;



    public CardDetailsAdapter(Context context, List<Card> cardList) {
        this.activity = (Activity) context;
        this.cardList = cardList;
    }

    @Override
    public CardDetailsAdapter.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(activity).inflate(R.layout.card_details_single, viewGroup, false);
        return new CardDetailsAdapter.viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final CardDetailsAdapter.viewHolder holder, final int position) {
        Card card = cardList.get(position);
        if (card.getCARD_OWNER().equalsIgnoreCase("")){
            holder.customerName.setVisibility(View.GONE);
        }else {
            holder.customerName.setText(card.getCARD_OWNER());
        }
        holder.created.setText(card.getCreated());
        holder.paymentMethod.setText(card.getPaymentStatus());

        holder.serviceTotal.setText("Service Charge : " + card.getServiceCharge());
        holder.qty.setText("Qty : " + card.getQty());
        holder.subTotal.setText("Sub Total : " + card.getTotal());
        double subTotalVal =  (card.getTotal()*card.getQty())+  card.getServiceCharge();
        holder.total.setText("Total : " +subTotalVal);

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, PaymentView.class);
                intent.putExtra("card_id", card.getCardId());
                activity.startActivity(intent);

            }
        });
        //holder.rootLayout.setOnClickListener(v -> onItemClickListener.onClickListener(customer.getCustomerId(), customer.getCustomerName()));
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView customerName, created, paymentMethod, serviceTotal, total,subTotal,qty;
        LinearLayout rootLayout;
        ImageButton payment;

        public viewHolder(View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            customerName = itemView.findViewById(R.id.customerName);
            created = itemView.findViewById(R.id.created);
            paymentMethod = itemView.findViewById(R.id.paymentMethod);
            serviceTotal = itemView.findViewById(R.id.serviceTotal);
            total = itemView.findViewById(R.id.total);
            subTotal = itemView.findViewById(R.id.subTotal);
            qty = itemView.findViewById(R.id.qty);
        }
    }




    public interface PaymentRefresh {
        void callRefresh();
    }

}

