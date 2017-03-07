package com.example.user.TourTalking.main;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.board.BoardActivity;
import com.example.user.TourTalking.domain.mian.CompanyBoard;
import com.example.user.TourTalking.domain.mian.TrvBoard;
import com.example.user.TourTalking.sharing.Fragments;
import com.example.user.TourTalking.sharing.ImageAsycnTask;
import com.example.user.TourTalking.sharing.MainActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-13.
 */

public class NoticeItem extends LinearLayout implements View.OnClickListener {
    public static final String TRVBOARD = "trvBoard";
    public static final String COMPBOARD = "compBoard";


    private LayoutInflater inflater;
    private int code;
    private Parcelable dto;
    private View view;
    private TextView itemName;
    private TextView itemTitle;
    public ImageView itemImage;
    private ImageAsycnTask asycnTask;
    private Context context;
    private ArrayList<Parcelable> datas = new ArrayList<Parcelable>();
    private String TAG;
    private String type;

    public NoticeItem(Context context, Parcelable dto, int code) {
        super(context);
        TAG = this.getClass().getSimpleName();
        this.context = context;
        this.code = code;
        this.dto = dto;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (code == Fragments.ANNOUNCE) {
            view = inflater.inflate(R.layout.main_notice_announce_item, this);
            init();
            setAnnData();
        } else if (code == Fragments.TRV_ITEM) {
            view = inflater.inflate(R.layout.main_notice_sep_item, this);
            init();
            setTrvData();
        }
        datas.add(dto);
        this.setOnClickListener(this);
    }

    public void setAnnData() {
        CompanyBoard companyBoard = (CompanyBoard) dto;
        itemName.setText(companyBoard.getCompany_name());
        itemTitle.setText(companyBoard.getCompany_board_title());
        asycnTask = new ImageAsycnTask(this);
        asycnTask.execute(companyBoard.getImage_url());
        asycnTask = null;
        type = COMPBOARD;
    }

    public void setTrvData() {
        TrvBoard trvBoard = (TrvBoard) dto;
        itemName.setText(trvBoard.getCompany_name());
        itemTitle.setText(trvBoard.getTrv_board_title());
        if (trvBoard.getTrvImageUrl().size() != 0) {
            asycnTask = new ImageAsycnTask(this);
            asycnTask.execute(trvBoard.getTrvImageUrl().get(0).getImage_url());
        }
        type = TRVBOARD;


    }


    public void init() {
        itemName = (TextView) view.findViewById(R.id.title);
        itemTitle = (TextView) view.findViewById(R.id.content);
        itemImage = (ImageView) view.findViewById(R.id.company_img);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, BoardActivity.class);
        intent.putExtra("type", type);
        intent.putParcelableArrayListExtra("data", datas);
        context.startActivity(intent);
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.fade_out);
    }
}
