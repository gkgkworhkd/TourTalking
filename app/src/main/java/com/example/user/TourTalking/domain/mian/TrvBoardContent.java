package com.example.user.TourTalking.domain.mian;

import android.os.Parcel;
import android.os.Parcelable;

public class TrvBoardContent implements Parcelable{
    private int board_content_id;
    private int trv_board_id;
    private String board_content;

    public int getBoard_content_id() {
        return board_content_id;
    }

    public void setBoard_content_id(int board_content_id) {
        this.board_content_id = board_content_id;
    }

    public int getTrv_board_id() {
        return trv_board_id;
    }

    public void setTrv_board_id(int trv_board_id) {
        this.trv_board_id = trv_board_id;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.board_content_id);
        dest.writeInt(this.trv_board_id);
        dest.writeString(this.board_content);
    }

    public TrvBoardContent() {
    }

    protected TrvBoardContent(Parcel in) {
        this.board_content_id = in.readInt();
        this.trv_board_id = in.readInt();
        this.board_content = in.readString();
    }

    public static final Creator<TrvBoardContent> CREATOR = new Creator<TrvBoardContent>() {
        @Override
        public TrvBoardContent createFromParcel(Parcel source) {
            return new TrvBoardContent(source);
        }

        @Override
        public TrvBoardContent[] newArray(int size) {
            return new TrvBoardContent[size];
        }
    };
}
