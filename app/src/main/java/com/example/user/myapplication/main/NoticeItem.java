package com.example.user.myapplication.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-13.
 */

public class NoticeItem extends LinearLayout{
    LayoutInflater inflater;
    TextView title,content;

    public NoticeItem(Context context) {
        super(context);
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.main_notice_layout_item,this);
        init();
    }
    public void init(){

    }
    //TODO 공지 데이터
    public void setData(){

    }
}
