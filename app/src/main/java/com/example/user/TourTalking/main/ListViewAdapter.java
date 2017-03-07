package com.example.user.TourTalking.main;

import android.content.Context;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.TourTalking.sharing.Fragments;

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
    private HashMap<String,List> data;
    String TAG;
    public ListViewAdapter(Context activity, int code, List list) {
        TAG=this.getClass().getSimpleName();
        context = activity;
        this.code = code;
        this.list=list;
        getData();
    }
    public void getData(){
        Log.d(TAG,list.size()+"길이는?");
        data=(HashMap<String, List>)list.get(0);
        ann_list=(ArrayList<Parcelable>)data.get("companyAnnounce");
        trv_list=(ArrayList<Parcelable>)data.get("trevelItem");
    }
    @Override
    public int getCount() {
        int result=6;
        if(code==Fragments.ANNOUNCE)result=3;
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
        NoticeItem item = null;
        Parcelable dto=null;

        if (code == Fragments.ANNOUNCE) {
            dto=ann_list.get(position);
        } else if (code == Fragments.TRV_ITEM) {
            dto=trv_list.get(position);
        }
        item = new NoticeItem(context, dto, code);
        view = item;
        return view;
    }


}
