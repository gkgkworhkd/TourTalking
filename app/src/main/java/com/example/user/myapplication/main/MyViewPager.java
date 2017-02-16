package com.example.user.myapplication.main;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by user on 2017-02-15.
 */

public class MyViewPager extends ViewPager {

    private boolean enabled;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        enabled=false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(enabled){
            return super.onTouchEvent(ev);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(this.enabled){
            return super.onInterceptTouchEvent(ev);
        }
        return false;
    }

    public void setPagingEnabled(){
        enabled=true;
    }
    public void setPagingDisabled(){
        enabled=false;
    }
}
