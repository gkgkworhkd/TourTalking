package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class TrvImageUrl implements Parcelable {
    private int trv_image_id;
    private String image_url;
    private String trv_image_content;

    public int getTrv_image_id() {
        return trv_image_id;
    }

    public void setTrv_image_id(int trv_image_id) {
        this.trv_image_id = trv_image_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getTrv_image_content() {
        return trv_image_content;
    }

    public void setTrv_image_content(String trv_image_content) {
        this.trv_image_content = trv_image_content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.trv_image_id);
        dest.writeString(this.image_url);
        dest.writeString(this.trv_image_content);
    }

    public TrvImageUrl() {
    }

    protected TrvImageUrl(Parcel in) {
        this.trv_image_id = in.readInt();
        this.image_url = in.readString();
        this.trv_image_content = in.readString();
    }

    public static final Creator<TrvImageUrl> CREATOR = new Creator<TrvImageUrl>() {
        @Override
        public TrvImageUrl createFromParcel(Parcel source) {
            return new TrvImageUrl(source);
        }

        @Override
        public TrvImageUrl[] newArray(int size) {
            return new TrvImageUrl[size];
        }
    };
}
