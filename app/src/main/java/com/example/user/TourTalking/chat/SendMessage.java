package com.example.user.TourTalking.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-02-16.
 */

public class SendMessage extends LinearLayout{
    LayoutInflater inflater;
    View view;
    String msg;
    public SendMessage(Context context,String msg) {
        super(context);
        this.msg=msg;
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.chat_item_send,this);
        setData();
    }
    public void setData(){
        TextView message=(TextView) view.findViewById(R.id.send_Message);
        message.setText(msg);
    }
}
