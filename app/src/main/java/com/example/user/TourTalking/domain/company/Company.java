package com.example.user.TourTalking.domain.company;

import android.os.Parcel;
import android.os.Parcelable;

public class Company implements Parcelable {
    private int company_id;
    private String company_name;
    private String company_intro;
    private String image_url;

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_intro() {
        return company_intro;
    }

    public void setCompany_intro(String company_intro) {
        this.company_intro = company_intro;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.company_id);
        dest.writeString(this.company_name);
        dest.writeString(this.company_intro);
        dest.writeString(this.image_url);
    }

    public Company() {
    }

    protected Company(Parcel in) {
        this.company_id = in.readInt();
        this.company_name = in.readString();
        this.company_intro = in.readString();
        this.image_url = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel source) {
            return new Company(source);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };
}
