package com.example.abiramy;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public int UserId;
    public String UserName;
    public String UserEmail;
    public int UserMobile;
    public String UserAddress;

    public User(){

    }

    public User(String name, String email, int mobile, String address){
        UserName = name;
        UserEmail = email;
        UserMobile = mobile;
        UserAddress = address;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public int getUserMobile() {
        return UserMobile;
    }

    public void setUserMobile(int userMobile) {
        UserMobile = userMobile;
    }

    public String getUserAddress() {
        return UserAddress;
    }

    public void setUserAddress(String userAddress) {
        UserAddress = userAddress;
    }


    protected User(Parcel parcel) {
        UserId = parcel.readInt();
        UserName = parcel.readString();
        UserEmail = parcel.readString();
        UserMobile = parcel.readInt();
        UserAddress = parcel.readString();

    }

    public static final Parcelable.Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(UserId);
        dest.writeString(UserName);
        dest.writeString(UserEmail);
        dest.writeInt(UserMobile);
        dest.writeString(UserAddress);

    }
}
