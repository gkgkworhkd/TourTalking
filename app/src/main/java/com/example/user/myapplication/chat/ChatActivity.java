package com.example.user.myapplication.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-16.
 */

public class ChatActivity extends AppCompatActivity{
    public static final int SENDMESSAGE=0;
    public static final int REVMESSAGE=1;

    ListView listView;
    int[] chatSize={0,1,1,1,0};
    ChatAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        listView=(ListView) findViewById(R.id.chat_listView);
        adapter=new ChatAdapter(this,chatSize);
        listView.setStackFromBottom(true);
        listView.setAdapter(adapter);
    }

}
