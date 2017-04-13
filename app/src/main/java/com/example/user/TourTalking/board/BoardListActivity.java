package com.example.user.TourTalking.board;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.sharing.MyAppCompatActivity;
import com.example.user.TourTalking.writeboard.WriteBoardActivity;
import com.example.user.TourTalking.writeboard.WriteTrvBoardActivity;

/**
 * Created by user on 2017-03-14.
 */

public class BoardListActivity extends MyAppCompatActivity {
    ListView listView;
    BoardListAsycnTask asycnTask;
    BoardListViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asycnTask = new BoardListAsycnTask(this);
        asycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "http://192.168.219.100:7777/device/boardList", "GET");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void init() {
        if (getIntent().getStringExtra("type")==null) {
            setContentView(R.layout.board_list_activity);
            listView = (ListView) findViewById(R.id.trv_board_listView);
        } else {
            setContentView(R.layout.board_company_list_activity);
            listView = (ListView) findViewById(R.id.trv_company_board_listView);
        }

        adapter = new BoardListViewAdapter(this);
        listView.setAdapter(adapter);
    }

    public void boardCompBt(View view) {
        Intent intent = new Intent(this, WriteTrvBoardActivity.class);
        startActivity(intent);
    }
}
