package com.example.user.myapplication.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import com.example.user.myapplication.R;
import com.example.user.myapplication.chat.ChatFragmentAdapter;
import com.example.user.myapplication.chat.Test;
import com.example.user.myapplication.company.CompanyActivity;

public class MainActivity extends AppCompatActivity {
    String TAG;
    Toolbar toolbar;
    MyPagerAdapter myPagerAdapter;
    ViewPager viewPager;
    static public MainActivity mainActivity;
    public ChatFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
        setContentView(R.layout.activity_main);
        mainActivity = this;
        init();
    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.fragment_containar);
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        //Log.d(TAG,listView_main+"이 생성되었다.");
        //SetUp ListView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.m1:
                //TODO 검색기능 활성화
                break;
            case R.id.m2:
                //TODO 로그인 기능 활성화

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void MainBt(View view) {
        switch (view.getId()) {
            case R.id.talk_img:
                viewPager.setCurrentItem(1);
                break;
            case R.id.trv_img:
                break;
            case R.id.spe_img:
                break;
            case R.id.home_toggle:
                viewPager.setCurrentItem(0);
                break;
        }
    }


}
