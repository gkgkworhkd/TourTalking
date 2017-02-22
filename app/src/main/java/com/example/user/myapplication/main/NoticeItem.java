package com.example.user.myapplication.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.domain.mian.Announce;
import com.example.user.myapplication.domain.mian.CompanyBoard;
import com.example.user.myapplication.domain.mian.TrvBoard;
import com.example.user.myapplication.sharing.Fragments;

/**
 * Created by user on 2017-02-13.
 */

public class NoticeItem extends LinearLayout {
    private LayoutInflater inflater;
    private int code;
    private Announce dto;
    private View view;

    public NoticeItem(Context context, Announce dto, int code) {
        super(context);
        this.code = code;
        this.dto = dto;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.main_notice_layout_item, this);
        setData();
    }

    public void setData() {
        TextView companyName = (TextView) view.findViewById(R.id.title);
        TextView companyTitle = (TextView) view.findViewById(R.id.content);
        if (code == Fragments.ANNOUNCE) {
            CompanyBoard companyBoard = (CompanyBoard) dto;
            companyName.setText(companyBoard.getCompany_name());
            companyTitle.setText(companyBoard.getCompany_board_title());
        } else if (code == Fragments.TRV_ITEM) {
            TrvBoard trvBoard =(TrvBoard)dto;
            companyName.setText(trvBoard.getCompany_name());
            companyTitle.setText(trvBoard.getTrv_board_title());
        }
    }
}
