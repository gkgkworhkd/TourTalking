package com.example.user.TourTalking.domain.mian;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
    private int city_id;
    private String country_name;
    private String city_name;

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.city_id);
        dest.writeString(this.country_name);
        dest.writeString(this.city_name);
    }

    public City() {
    }

    protected City(Parcel in) {
        this.city_id = in.readInt();
        this.country_name = in.readString();
        this.city_name = in.readString();
    }

    public static final Creator<City> CREATOR = new Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
