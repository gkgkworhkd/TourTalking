package com.example.user.myapplication.main;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.myapplication.sharing.Fragments;

/**
 * Created by user on 2017-02-13.
 */

public class ListViewAdapter extends BaseAdapter{

    Context context;
    int code;
    public ListViewAdapter(Context activity,int code){
        context=activity;
        this.code=code;
    }

    @Override
    public int getCount() {
        return 3;
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
        View view=null;
        NoticeItem item=null;
        if(code== Fragments.ANNOUNCE){
            //TODO DTO,DAO작업 및 리스트뷰미완성
            item=new NoticeItem(context);
            view=item;
        }else if(code== Fragments.SEP_ITEM){
            item=new NoticeItem(context);
            view=item;
        }else if(code== Fragments.TRV_ITEM){
            item=new NoticeItem(context);
            view=item;
        }

        return view;
    }
}
