package com.example.user.TourTalking.domain.chat;

/**
 * Created by user on 2017-02-16.
 */

public class MemberList {
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
}
