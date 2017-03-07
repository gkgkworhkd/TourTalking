package com.example.user.TourTalking.domain.mian;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by user on 2017-02-20.
 */

public class Country implements Parcelable {
    private int continent_id;
    private String country_name;
    private List<City> city;

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.continent_id);
        dest.writeString(this.country_name);
        dest.writeTypedList(this.city);
    }

    public Country() {
    }

    protected Country(Parcel in) {
        this.continent_id = in.readInt();
        this.country_name = in.readString();
        this.city = in.createTypedArrayList(City.CREATOR);
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
