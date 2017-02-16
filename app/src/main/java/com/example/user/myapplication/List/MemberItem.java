package com.example.user.myapplication.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.chat.MemberList;

/**
 * Created by user on 2017-02-16.
 */

public class MemberItem extends LinearLayout {
    private LayoutInflater inflater;
    private View view;
    private MemberList dto;
    public MemberItem(Context context, MemberList dto) {
        super(context);
        this.dto=dto;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.chatlist_member_item,this);
    }
    public void setData(View view){
        TextView nickName=(TextView) view.findViewById(R.id.chat_member_name);
        nickName.setText(dto.getNickName());
        //TODO 멤버리스트에 이미지 적용
    }
}
