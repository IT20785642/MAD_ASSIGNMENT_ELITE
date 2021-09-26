package com.example.abiramy.Cart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.abiramy.R;

import java.util.ArrayList;

public class CartViewAdapter extends ArrayAdapter {
    public CartViewAdapter(@NonNull Context context, ArrayList<Cart> cakes) {
        super(context, R.layout.cart_custom_row, cakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View cartView = inflater.inflate(R.layout.cart_custom_row, parent, false);

        Cart cart = (Cart) getItem(position);

        String item_name= cart.C_item;
        String item_size = cart.C_size;
        int item_order = cart.C_no_order;
        String item_desc = cart.C_desc;

        TextView vitem_name = (TextView) cartView.findViewById(R.id.type);
        TextView vitem_size = (TextView) cartView.findViewById(R.id.size);
        TextView vitem_order = (TextView) cartView.findViewById(R.id.order);
        TextView vitem_desc = (TextView) cartView.findViewById(R.id.descri);
        ImageView userIcon = (ImageView) cartView.findViewById(R.id.userIcon);

        vitem_name.setText(item_name);
        vitem_size.setText(item_size);
        vitem_order.setText(Integer.toString(item_order));
        vitem_desc.setText(item_desc);

        return cartView;
    }
}

