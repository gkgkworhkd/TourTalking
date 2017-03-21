package com.example.user.TourTalking.board;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.chat.ChatActivity;

/**
 * Created by user on 2017-02-27.
 */

public class BoardActivity extends AppCompatActivity{
    private ViewPager viewPager;
    private BoardViewPageAdapter adapter;
    private Intent intent;
    private String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=this.getClass().getSimpleName();
        intent=getIntent();
        //Log.d(TAG,"받아온 데이터의 개수는? : "+data.size());
        setContentView(R.layout.board_activity);
        init();
    }
    public void init(){
        viewPager=(ViewPager) findViewById(R.id.board_viewPager);
        adapter=new BoardViewPageAdapter(getSupportFragmentManager());
        adapter.fragment.dto=intent.getParcelableArrayListExtra("data").get(0);
        adapter.fragment.type=intent.getStringExtra("type");
        viewPager.setAdapter(adapter);
    }
}
