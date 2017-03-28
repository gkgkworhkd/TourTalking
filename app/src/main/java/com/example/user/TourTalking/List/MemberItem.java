package com.example.user.TourTalking.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.MemberList;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

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
        setData();

    }
    public void setData(){
        TextView nickName=(TextView) view.findViewById(R.id.chat_member_name);
        ImageView imageView=(ImageView)view.findViewById(R.id.chat_member_img);
        ImageAsycnTask imageAsycnTask=new ImageAsycnTask(imageView,200);
        imageAsycnTask.execute(dto.getImg());
        nickName.setText(dto.getNickName());

        //TODO 멤버리스트에 이미지 적용
    }
}
