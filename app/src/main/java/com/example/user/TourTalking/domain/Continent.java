package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2017-02-20.
 */

public class Continent implements Parcelable {
    private int continent_id;
    private String continent_name;

    public int getContinent_id() {
        return continent_id;
    }

    public void setContinent_id(int continent_id) {
        this.continent_id = continent_id;
    }

    public String getContinent_name() {
        return continent_name;
    }

    public void setContinent_name(String continent_name) {
        this.continent_name = continent_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.continent_id);
        dest.writeString(this.continent_name);
    }

    public Continent() {
    }

    protected Continent(Parcel in) {
        this.continent_id = in.readInt();
        this.continent_name = in.readString();
    }

    public static final Creator<Continent> CREATOR = new Creator<Continent>() {
        @Override
        public Continent createFromParcel(Parcel source) {
            return new Continent(source);
        }

        @Override
        public Continent[] newArray(int size) {
            return new Continent[size];
        }
    };
}
