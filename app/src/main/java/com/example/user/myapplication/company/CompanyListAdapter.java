package com.example.user.myapplication.company;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyListAdapter extends BaseAdapter{
    Context context;

    public CompanyListAdapter(Context context){
    this.context=context;
    }
    @Override
    public int getCount() {
        return 8;
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
        CompanyItem companyItem=new CompanyItem(context);
        view=companyItem;
        return view;
    }
}
