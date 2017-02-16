package com.example.user.myapplication.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-14.
 */

public class ChatItem extends LinearLayout {
    LayoutInflater inflater;
    public ChatItem(Context context) {
        super(context);
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.chat_item,this);
        setData();
    }

    //TODO DB에서 채팅정보 및 데이터 가져오기
    public void setData(){

    }
}
