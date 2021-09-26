package com.example.abiramy.Cart;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Cart implements Parcelable {
    public int CartId;
    public String C_item;
    public String C_size;
    public int C_no_order;
    public String C_desc;

    public Cart(){

    }
    public Cart(String item,String size,int no_order,String desc){
        C_item = item;
        C_size = size;
        C_no_order = no_order;
        C_desc = desc;

    }

    public int getCartId() { return CartId; }

    public void setCartId(int cartId) { CartId = cartId; }

    public String getC_item() { return C_item; }

    public void setC_item(String c_item) { C_item = c_item; }

    public String getC_size() { return C_size; }

    public void setC_size(String c_size) { C_size = c_size; }

    public int getC_no_order() { return C_no_order; }

    public void setC_no_order(int c_no_order) { C_no_order = c_no_order; }

    public String getC_desc() { return C_desc; }

    public void setC_desc(String c_desc) { C_desc = c_desc; }


    protected Cart(Parcel in) {
        CartId=in.readInt();
        C_item = in.readString();
        C_size = in.readString();
        C_no_order = in.readInt();
        C_desc = in.readString();
    }

    public static final Parcelable.Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(CartId);
        parcel.writeString(C_item);
        parcel.writeString(C_size);
        parcel.writeInt(C_no_order);
        parcel.writeString(C_desc);
    }
}
