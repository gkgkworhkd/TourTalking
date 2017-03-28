package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2017-02-16.
 */

public class MemberList implements Parcelable{
    private int member_id;
    private String img;
    private String nickName;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.member_id);
        dest.writeString(this.img);
        dest.writeString(this.nickName);
    }

    public MemberList() {
    }

    protected MemberList(Parcel in) {
        this.member_id = in.readInt();
        this.img = in.readString();
        this.nickName = in.readString();
    }

    public static final Creator<MemberList> CREATOR = new Creator<MemberList>() {
        @Override
        public MemberList createFromParcel(Parcel source) {
            return new MemberList(source);
        }

        @Override
        public MemberList[] newArray(int size) {
            return new MemberList[size];
        }
    };
}
