package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by user on 2017-02-20.
 */

public class Country implements Parcelable {
    private int country_id;
    private int continent_id;
    private String country_name;
    private List<City> city;
    private String country_image;

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getContinent_id() {
        return continent_id;
    }

    public void setContinent_id(int continent_id) {
        this.continent_id = continent_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public List<City> getCity() {
        return city;
    }

    public void setCity(List<City> city) {
        this.city = city;
    }

    public String getCountry_image() {
        return country_image;
    }

    public void setCountry_image(String country_image) {
        this.country_image = country_image;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.country_id);
        dest.writeInt(this.continent_id);
        dest.writeString(this.country_name);
        dest.writeTypedList(this.city);
        dest.writeString(this.country_image);
    }

    public Country() {
    }

    protected Country(Parcel in) {
        this.country_id = in.readInt();
        this.continent_id = in.readInt();
        this.country_name = in.readString();
        this.city = in.createTypedArrayList(City.CREATOR);
        this.country_image = in.readString();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel source) {
            return new Country(source);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };
}
