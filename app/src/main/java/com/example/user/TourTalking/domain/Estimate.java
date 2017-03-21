package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2017-03-20.
 */

public class Estimate implements Parcelable{
    private int estimate_id;
    private int city_id;
    private String startpoint;
    private int plan;
    private int adult;
    private int child;
    private int payment;
    private String trvPurpose;
    private String stay;
    private String stayStyle;
    private String request;

    public int getEstimate_id() {
        return estimate_id;
    }

    public void setEstimate_id(int estimate_id) {
        this.estimate_id = estimate_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(String startpoint) {
        this.startpoint = startpoint;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public String getTrvPurpose() {
        return trvPurpose;
    }

    public void setTrvPurpose(String trvPurpose) {
        this.trvPurpose = trvPurpose;
    }

    public String getStay() {
        return stay;
    }

    public void setStay(String stay) {
        this.stay = stay;
    }

    public String getStayStyle() {
        return stayStyle;
    }

    public void setStayStyle(String stayStyle) {
        this.stayStyle = stayStyle;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.estimate_id);
        dest.writeInt(this.city_id);
        dest.writeString(this.startpoint);
        dest.writeInt(this.plan);
        dest.writeInt(this.adult);
        dest.writeInt(this.child);
        dest.writeInt(this.payment);
        dest.writeString(this.trvPurpose);
        dest.writeString(this.stay);
        dest.writeString(this.stayStyle);
        dest.writeString(this.request);
    }

    public Estimate() {
    }

    protected Estimate(Parcel in) {
        this.estimate_id = in.readInt();
        this.city_id = in.readInt();
        this.startpoint = in.readString();
        this.plan = in.readInt();
        this.adult = in.readInt();
        this.child = in.readInt();
        this.payment = in.readInt();
        this.trvPurpose = in.readString();
        this.stay = in.readString();
        this.stayStyle = in.readString();
        this.request = in.readString();
    }

    public static final Creator<Estimate> CREATOR = new Creator<Estimate>() {
        @Override
        public Estimate createFromParcel(Parcel source) {
            return new Estimate(source);
        }

        @Override
        public Estimate[] newArray(int size) {
            return new Estimate[size];
        }
    };
}
