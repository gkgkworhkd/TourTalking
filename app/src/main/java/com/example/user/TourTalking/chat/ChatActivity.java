package com.example.user.TourTalking.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.user.TourTalking.R;

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
        setContentView(R.layout.chat_activity);
        listView=(ListView) findViewById(R.id.chat_listView);
        adapter=new ChatAdapter(this,chatSize);
        listView.setStackFromBottom(true);
        listView.setAdapter(adapter);
    }
    public void Chat_OnClick(View view){
       finish();
    }
}
