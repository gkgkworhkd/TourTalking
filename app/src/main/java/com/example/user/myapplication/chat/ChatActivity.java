package com.example.user.myapplication.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.example.user.myapplication.R;
import com.example.user.myapplication.sharing.MainActivity;

/**
 * Created by user on 2017-02-14.
 */

public class ChatActivity extends AppCompatActivity {
    ViewPager viewPager;
    ChatFragmentAdapter adapter;
    String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setContentView(R.layout.activity_chat);
        viewPager = (ViewPager) findViewById(R.id.fragment_chat);
        // Log.d(TAG,"생성된 매니져는"+MainActivity.mainActivity.getSupportFragmentManager());
        // Log.d(TAG,"생성된 매니져는"+getSupportFragmentManager());
        adapter=new ChatFragmentAdapter(getSupportFragmentManager());
        ListFragments fragments = new ListFragments();
        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_chat,fragments).commit();
        Log.d(TAG,"페이져 생성"+viewPager);
        viewPager.setAdapter(adapter);
        //viewPager.setAdapter(adapter);
    }
}
