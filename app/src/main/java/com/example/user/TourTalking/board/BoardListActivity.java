package com.example.user.TourTalking.board;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-03-14.
 */

public class BoardListActivity extends AppCompatActivity{
    ListView listView;
    BoardListAsycnTask asycnTask;
    BoardListViewAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        asycnTask=new BoardListAsycnTask(this);
        asycnTask.execute("http://192.168.219.101:7777/device/boardList","GET");
    }
    public void init(){
        setContentView(R.layout.board_list_activity);
        listView=(ListView)findViewById(R.id.trv_board_listView);
        adapter=new BoardListViewAdapter(this);
        listView.setAdapter(adapter);
    }
}
