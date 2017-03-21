package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2017-02-16.
 */

public class CompanyBoard implements Parcelable{
    private int company_board_id;
    private String company_board_title;
    private String company_board_content;
    private String company_board_regdate;
    private int company_board_hit;
    private String company_name;
    private String image_url;

    public int getCompany_board_id() {
        return company_board_id;
    }

    public void setCompany_board_id(int company_board_id) {
        this.company_board_id = company_board_id;
    }

    public String getCompany_board_title() {
        return company_board_title;
    }

    public void setCompany_board_title(String company_board_title) {
        this.company_board_title = company_board_title;
    }

    public String getCompany_board_content() {
        return company_board_content;
    }

    public void setCompany_board_content(String company_board_content) {
        this.company_board_content = company_board_content;
    }

    public String getCompany_board_regdate() {
        return company_board_regdate;
    }

    public void setCompany_board_regdate(String company_board_regdate) {
        this.company_board_regdate = company_board_regdate;
    }

    public int getCompany_board_hit() {
        return company_board_hit;
    }

    public void setCompany_board_hit(int company_board_hit) {
        this.company_board_hit = company_board_hit;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public static Creator<CompanyBoard> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.company_board_id);
        dest.writeString(this.company_board_title);
        dest.writeString(this.company_board_content);
        dest.writeString(this.company_board_regdate);
        dest.writeInt(this.company_board_hit);
        dest.writeString(this.company_name);
        dest.writeString(this.image_url);
    }

    public CompanyBoard() {
    }

    protected CompanyBoard(Parcel in) {
        this.company_board_id = in.readInt();
        this.company_board_title = in.readString();
        this.company_board_content = in.readString();
        this.company_board_regdate = in.readString();
        this.company_board_hit = in.readInt();
        this.company_name = in.readString();
        this.image_url = in.readString();
    }

    public static final Creator<CompanyBoard> CREATOR = new Creator<CompanyBoard>() {
        @Override
        public CompanyBoard createFromParcel(Parcel source) {
            return new CompanyBoard(source);
        }

        @Override
        public CompanyBoard[] newArray(int size) {
            return new CompanyBoard[size];
        }
    };
}
