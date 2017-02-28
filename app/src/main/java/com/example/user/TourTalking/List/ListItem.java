package com.example.user.TourTalking.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.chat.ChatList;
import com.example.user.TourTalking.domain.chat.MemberList;

/**
 * Created by user on 2017-02-14.
 */

public class ListItem extends LinearLayout {
    private LayoutInflater inflater;
    private ChatList chatList;
    private MemberList memberList;
    private View view;
    private ChatList dto;
    public ListItem(Context context, ChatList dto) {
        super(context);
        this.dto=dto;
        init(context);
    }

    public void init(Context context){
        inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.chatlist_list_item,this);
        setData();
    }
    public void setData(){
        TextView nickName=(TextView) view.findViewById(R.id.chat_list_member);
        TextView content=(TextView)view.findViewById(R.id.chat_list_content);
        nickName.setText(dto.getNickName());
        content.setText(dto.getContent());
        //TODO 리스트 아이템 이미지 등록

    }

}
