package com.example.user.myapplication.company;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyItem extends LinearLayout{

    public CompanyItem(Context context) {
        super(context);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.company_info_item,this);
    }
    public void setData(){

    }
}
