package com.example.user.TourTalking.country_list;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by user on 2017-03-07.
 */

public class CityListLayout extends LinearLayout{
    public TextView textView;
    public CityListLayout(Context context) {
        super(context);
        setBackgroundColor(Color.WHITE);
        setOrientation(LinearLayout.HORIZONTAL);
        textView=new TextView(context);
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
        textView.setGravity(Gravity.CENTER);
        setPadding(5,5,0,5);
        textView.setBackgroundColor(Color.parseColor("#292d38"));
        textView.setTextColor(Color.parseColor("#919191"));
        addView(textView);
    }

}
