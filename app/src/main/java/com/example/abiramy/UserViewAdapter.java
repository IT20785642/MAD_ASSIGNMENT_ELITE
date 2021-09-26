package com.example.abiramy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserViewAdapter extends ArrayAdapter {
    public UserViewAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, R.layout.user_custom_row, users);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View userView = inflater.inflate(R.layout.user_custom_row, parent, false);

        User user = (User) getItem(position);
        String user_name =  user.UserName;
        String user_email = user.UserEmail;
        int user_mobile = user.UserMobile;
        String user_address = user.UserAddress;


        TextView vuser_name = (TextView) userView.findViewById(R.id.type);
        TextView vuser_email = (TextView) userView.findViewById(R.id.size);
        TextView vuser_mobile = (TextView) userView.findViewById(R.id.order);
        TextView vuser_address = (TextView) userView.findViewById(R.id.descri);
        ImageView userIcon = (ImageView) userView.findViewById(R.id.userIcon);


        vuser_name.setText(user_name);
        vuser_email.setText(user_email);
        vuser_mobile.setText(Integer.toString(user_mobile));
        vuser_address.setText(user_address);

        return userView;
    }

}
