package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Customer implements Parcelable {
    private int customer_pk;
    private String customer_id;
    private String customer_passwoard;
    private String customer_nickname;
    private String customer_name;
    private String customer_phone;
    private String customer_gender;

    public int getCustomer_pk() {
        return customer_pk;
    }

    public void setCustomer_pk(int customer_pk) {
        this.customer_pk = customer_pk;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_passwoard() {
        return customer_passwoard;
    }

    public void setCustomer_passwoard(String customer_passwoard) {
        this.customer_passwoard = customer_passwoard;
    }

    public String getCustomer_nickname() {
        return customer_nickname;
    }

    public void setCustomer_nickname(String customer_nickname) {
        this.customer_nickname = customer_nickname;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.customer_pk);
        dest.writeString(this.customer_id);
        dest.writeString(this.customer_passwoard);
        dest.writeString(this.customer_nickname);
        dest.writeString(this.customer_name);
        dest.writeString(this.customer_phone);
        dest.writeString(this.customer_gender);
    }

    public Customer() {
    }

    protected Customer(Parcel in) {
        this.customer_pk = in.readInt();
        this.customer_id = in.readString();
        this.customer_passwoard = in.readString();
        this.customer_nickname = in.readString();
        this.customer_name = in.readString();
        this.customer_phone = in.readString();
        this.customer_gender = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel source) {
            return new Customer(source);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}
