package com.example.user.TourTalking.main;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.CompanyBoard;
import com.example.user.TourTalking.sharing.Fragments;
import com.example.user.TourTalking.sharing.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-13.
 */

public class ListViewAdapter extends BaseAdapter {
    private List list;
    private Context context;
    private int code;
    private ArrayList<Parcelable> ann_list;
    private ArrayList<Parcelable> trv_list;
    private HashMap<String, List> data;
    String TAG;

    public ListViewAdapter(Context activity, int code, List list) {
        TAG = this.getClass().getSimpleName();
        context = activity;
        // this.code = code;
        this.list = list;
        getData();
    }

    public void getData() {
        data = (HashMap<String, List>) list.get(0);
        ann_list = (ArrayList<Parcelable>) data.get("companyAnnounce");
        trv_list = (ArrayList<Parcelable>) data.get("trevelItem");
    }

    @Override
    public int getCount() {
        int result = 11;
        //if(code==Fragments.ANNOUNCE)result=3;
        return result;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        Parcelable dto = null;

        /*if (code == Fragments.ANNOUNCE) {
            dto=ann_list.get(position);
        } else if (code == Fragments.TRV_ITEM) {
            dto=trv_list.get(position);
        }*/
        if(position==0){
            view=setTitle("회사 공지사항");
            code=211;
        } else if (position < 4 && position!=0) {
            dto = ann_list.get(position-1);
            CompanyBoard comp = (CompanyBoard) dto;
            code = Fragments.ANNOUNCE;
            view = new NoticeItem(context, dto, code);
        }else if(position==4){
            view=setTitle("여행 상품");
            code=111;
        } else if(position>4){
            dto = trv_list.get(position - 5);
            code = Fragments.TRV_ITEM;
            view = new NoticeItem(context, dto, code);
        }

        return view;
    }
    public View setTitle(String text){
        LayoutInflater inflater=(LayoutInflater) MainActivity.mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.main_board_title,null);
        TextView board_title=(TextView) view.findViewById(R.id.main_board_text);
        board_title.setText(text);
        return view;
    }


}
