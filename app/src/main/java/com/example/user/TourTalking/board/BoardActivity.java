package com.example.user.TourTalking.board;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-02-27.
 */

public class BoardActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private BoardViewPageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        intent.getParcelableArrayExtra("");
        setContentView(R.layout.board_activity);
        init();
    }
    public void init(){
        viewPager=(ViewPager) findViewById(R.id.board_viewPager);
        adapter=new BoardViewPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public void boardOnClick(View view){
        if(view.getId()==R.id.board_inquiry){

        }else if(view.getId()==R.id.board_req){

        }
    }
}
