package com.example.user.myapplication.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-14.
 */

public class ListActivity extends AppCompatActivity {
    ViewPager viewPager;
    ListFragmentAdapter adapter;
    String TAG;
    public static final int MEMVER_LIST = 0;
    public static final int CHAT_LIST = 1;
    private int selectedPageNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setContentView(R.layout.activity_chatlist);
        viewPager = (ViewPager) findViewById(R.id.fragment_chat);
        // Log.d(TAG,"생성된 매니져는"+MainActivity.mainActivity.getSupportFragmentManager());
        // Log.d(TAG,"생성된 매니져는"+getSupportFragmentManager());
        adapter = new ListFragmentAdapter(getSupportFragmentManager());
        Log.d(TAG, "페이져 생성" + viewPager);
        viewPager.setAdapter(adapter);


    }

    public void ChatActivityOnClick(View view) {
        if(view.getId()==R.id.chat_backImg){
            this.finish();
        }else{
            if (view.getId() == R.id.chat_member) {
                selectedPageNum = 0;
                findViewById(R.id.message_chat).setBackgroundColor(Color.WHITE);
            }else if(view.getId()==R.id.message_chat){
                selectedPageNum=1;
                findViewById(R.id.chat_member).setBackgroundColor(Color.WHITE);
            }
            view.setBackgroundColor(Color.GRAY);
            viewPager.setCurrentItem(selectedPageNum);
        }

    }


}
