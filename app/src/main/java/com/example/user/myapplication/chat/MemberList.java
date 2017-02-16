package com.example.user.myapplication.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.user.myapplication.R;

/**
 * Created by 고재광 on 2017-02-14.
 */

public class MemberList extends AppCompatActivity{
    ViewPager viewPager;
    ChatFragmentAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        viewPager=(ViewPager) findViewById(R.id.fragment_chat);
        adapter=new ChatFragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
