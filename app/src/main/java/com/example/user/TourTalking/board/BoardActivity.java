package com.example.user.TourTalking.board;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.chat.ChatActivity;
import com.example.user.TourTalking.sharing.MyAppCompatActivity;
import com.example.user.TourTalking.writeboard.WriteTrvBoardActivity;

/**
 * Created by user on 2017-02-27.
 */

public class BoardActivity extends MyAppCompatActivity {
    private ViewPager viewPager;
    public BoardViewPageAdapter adapter;
    private Intent intent;
    private String TAG;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        intent = getIntent();
        //Log.d(TAG,"받아온 데이터의 개수는? : "+data.size());
        setContentView(R.layout.board_activity);
        init();
    }

    public void init() {
        viewPager = (ViewPager) findViewById(R.id.board_viewPager);
        adapter = new BoardViewPageAdapter(getSupportFragmentManager());
        adapter.fragment.dto = intent.getParcelableArrayListExtra("data").get(0);
        adapter.fragment.type = intent.getStringExtra("type");
        viewPager.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void boardCompBt(View view) {
        Intent intent = new Intent(this, WriteTrvBoardActivity.class);
        startActivity(intent);
    }
}
