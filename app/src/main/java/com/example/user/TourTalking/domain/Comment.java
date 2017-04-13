package com.example.user.TourTalking.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2017-04-05.
 */

public class Comment implements Parcelable{
    private int comment_id;
    private int trv_board_id;
    private int member_id;
    private String commnet;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getTrv_board_id() {
        return trv_board_id;
    }

    public void setTrv_board_id(int trv_board_id) {
        this.trv_board_id = trv_board_id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getCommnet() {
        return commnet;
    }

    public void setCommnet(String commnet) {
        this.commnet = commnet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.comment_id);
        dest.writeInt(this.trv_board_id);
        dest.writeInt(this.member_id);
        dest.writeString(this.commnet);
    }

    public Comment() {
    }

    protected Comment(Parcel in) {
        this.comment_id = in.readInt();
        this.trv_board_id = in.readInt();
        this.member_id = in.readInt();
        this.commnet = in.readString();
    }

    public static final Creator<Comment> CREATOR = new Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}
