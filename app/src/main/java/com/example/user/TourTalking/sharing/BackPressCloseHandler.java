package com.example.user.TourTalking.sharing;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by user on 2017-02-16.
 */

public class BackPressCloseHandler {
    private long backKetPressedTime=0;
    private Toast toast;
    private Activity activity;

    public BackPressCloseHandler(Activity activity){
        this.activity=activity;
    }
    public void onBackPressed(){
        if(System.currentTimeMillis()>backKetPressedTime+2000){
            backKetPressedTime=System.currentTimeMillis();
            showGuide();
            return;
        }else if(System.currentTimeMillis()<=backKetPressedTime+2000){
            activity.finish();
            toast.cancel();
        }
    }
    public void showGuide(){
        toast=Toast.makeText(activity,"\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT);
        toast.show();
    }
}
