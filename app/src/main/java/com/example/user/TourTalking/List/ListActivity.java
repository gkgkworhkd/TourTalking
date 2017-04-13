package com.example.user.TourTalking.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.sharing.MyAppCompatActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-14.
 */

public class ListActivity extends MyAppCompatActivity {
    private ViewPager viewPager;
    private ListFragmentAdapter adapter;
    private String TAG;
    private LinearLayout fr_list, ch_list;
    public static final int MEMVER_LIST = 0;
    public static final int CHAT_LIST = 1;
    private int selectedPageNum;
    public ArrayList<Parcelable> arr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        setContentView(R.layout.chatlist_activity);
        viewPager = (ViewPager) findViewById(R.id.fragment_chat);
        // Log.d(TAG,"생성된 매니져는"+MainActivity.mainActivity.getSupportFragmentManager());
        // Log.d(TAG,"생성된 매니져는"+getSupportFragmentManager());
        arr = getIntent().getParcelableArrayListExtra("friendList");
        adapter = new ListFragmentAdapter(getSupportFragmentManager());
        Log.d(TAG, "페이져 생성" + viewPager);
        viewPager.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_list_toolbar);
        setSupportActionBar(toolbar);
        fr_list=(LinearLayout)findViewById(R.id.fr_list);
        fr_list.setBackgroundColor(Color.GRAY);
        ch_list=(LinearLayout)findViewById(R.id.ch_list);
    }

    public void ChatActivityOnClick(View view) {

        if (view.getId() == R.id.chat_member) {
            selectedPageNum = 0;
            ch_list.setBackgroundColor(Color.WHITE);
            fr_list.setBackgroundColor(Color.GRAY);
        } else if (view.getId() == R.id.message_chat) {
            selectedPageNum = 1;
            fr_list.setBackgroundColor(Color.WHITE);
            ch_list.setBackgroundColor(Color.GRAY);
        }
        viewPager.setCurrentItem(selectedPageNum);

    }


}
