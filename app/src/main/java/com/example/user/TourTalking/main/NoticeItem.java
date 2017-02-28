package com.example.user.TourTalking.main;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.mian.Announce;
import com.example.user.TourTalking.domain.mian.CompanyBoard;
import com.example.user.TourTalking.domain.mian.TrvBoard;
import com.example.user.TourTalking.sharing.Fragments;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by user on 2017-02-13.
 */

public class NoticeItem extends LinearLayout {
    private LayoutInflater inflater;
    private int code;
    private Announce dto;
    private View view;
    private TextView itemName;
    private TextView itemTitle;
    public ImageView itemImage;
    private ImageAsycnTask asycnTask;
    public NoticeItem(Context context, Announce dto, int code) {
        super(context);
        this.code = code;
        this.dto = dto;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if(code == Fragments.ANNOUNCE){
            view = inflater.inflate(R.layout.main_notice_announce_item, this);
            init();
            setAnnData();
        }else if (code == Fragments.TRV_ITEM){
            view = inflater.inflate(R.layout.main_notice_sep_item, this);
            init();
            setTrvData();
        }

    }
    public void setAnnData(){
        CompanyBoard companyBoard = (CompanyBoard) dto;
        itemName.setText(companyBoard.getCompany_name());
        itemTitle.setText(companyBoard.getCompany_board_title());
        asycnTask=new ImageAsycnTask(this);
        asycnTask.execute(companyBoard.getImage_url());
    }

    public void setTrvData(){
        TrvBoard trvBoard =(TrvBoard)dto;
        itemName.setText(trvBoard.getCompany_name());
        itemTitle.setText(trvBoard.getTrv_board_title());
        if(trvBoard.getTrvImageUrl().size()!=0){
            asycnTask=new ImageAsycnTask(this);
            asycnTask.execute(trvBoard.getTrvImageUrl().get(0).getImage_url());
        }

    }


    public void init() {
        itemName = (TextView) view.findViewById(R.id.title);
        itemTitle = (TextView) view.findViewById(R.id.content);
        itemImage=(ImageView) view.findViewById(R.id.company_img);
    }


}
