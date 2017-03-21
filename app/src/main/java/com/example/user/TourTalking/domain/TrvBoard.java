package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-02-16.
 */

public class TrvBoard implements Parcelable {

    private int trv_board_id;
    private String company_name;
    private String city_name;
    private String trv_board_title;
    private String trv_board_regdate;
    private List<TrvImageUrl> trvImageUrl;
    private int trv_board_hit;
    private List<TrvBoardContent> trvBoardContent;

    public int getTrv_board_id() {
        return trv_board_id;
    }

    public void setTrv_board_id(int trv_board_id) {
        this.trv_board_id = trv_board_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getTrv_board_title() {
        return trv_board_title;
    }

    public void setTrv_board_title(String trv_board_title) {
        this.trv_board_title = trv_board_title;
    }

    public String getTrv_board_regdate() {
        return trv_board_regdate;
    }

    public void setTrv_board_regdate(String trv_board_regdate) {
        this.trv_board_regdate = trv_board_regdate;
    }

    public List<TrvImageUrl> getTrvImageUrl() {
        return trvImageUrl;
    }

    public void setTrvImageUrl(List<TrvImageUrl> trvImageUrl) {
        this.trvImageUrl = trvImageUrl;
    }

    public int getTrv_board_hit() {
        return trv_board_hit;
    }

    public void setTrv_board_hit(int trv_board_hit) {
        this.trv_board_hit = trv_board_hit;
    }

    public List<TrvBoardContent> getTrvBoardContent() {
        return trvBoardContent;
    }

    public void setTrvBoardContent(List<TrvBoardContent> trvBoardContent) {
        this.trvBoardContent = trvBoardContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.trv_board_id);
        dest.writeString(this.company_name);
        dest.writeString(this.city_name);
        dest.writeString(this.trv_board_title);
        dest.writeString(this.trv_board_regdate);
        dest.writeTypedList(this.trvImageUrl);
        dest.writeInt(this.trv_board_hit);
        dest.writeList(this.trvBoardContent);
    }

    public TrvBoard() {
    }

    protected TrvBoard(Parcel in) {
        this.trv_board_id = in.readInt();
        this.company_name = in.readString();
        this.city_name = in.readString();
        this.trv_board_title = in.readString();
        this.trv_board_regdate = in.readString();
        this.trvImageUrl = in.createTypedArrayList(TrvImageUrl.CREATOR);
        this.trv_board_hit = in.readInt();
        this.trvBoardContent = new ArrayList<TrvBoardContent>();
        in.readList(this.trvBoardContent, TrvBoardContent.class.getClassLoader());
    }

    public static final Creator<TrvBoard> CREATOR = new Creator<TrvBoard>() {
        @Override
        public TrvBoard createFromParcel(Parcel source) {
            return new TrvBoard(source);
        }

        @Override
        public TrvBoard[] newArray(int size) {
            return new TrvBoard[size];
        }
    };
}
