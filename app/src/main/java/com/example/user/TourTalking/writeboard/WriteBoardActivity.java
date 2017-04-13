package com.example.user.TourTalking.writeboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-03-21.
 */

public class WriteBoardActivity extends AppCompatActivity{
    ViewPager viewPager;
    WriteBoardPageAdapter writeBoardPageAdapter;
    int pageNum;
    String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_board_activity);
        TAG=this.getClass().getSimpleName();
        viewPager=(ViewPager)findViewById(R.id.write_board_viewPager);
        writeBoardPageAdapter=new WriteBoardPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(writeBoardPageAdapter);
    }
    public void writeBoardBt(View view){
        int id=view.getId();
        pageNum=viewPager.getCurrentItem();
        if(pageNum>=0&&pageNum<4){
            if(id==R.id.write_before){
                pageNum--;
            }else if(id==R.id.write_next){
                pageNum++;
            }
        }
        Log.d(TAG,pageNum+"현재 페이지번호는");
        viewPager.setCurrentItem(pageNum);
    }
}
