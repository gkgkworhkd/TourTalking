package com.example.user.TourTalking.chat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.TourTalking.R;

/**
 * Created by user on 2017-02-16.
 */

public class ChatActivity extends AppCompatActivity{
    public static final int SENDMESSAGE=0;
    public static final int REVMESSAGE=1;
    private ChatAsyncTask asyncTask;
    private TextView sendMsg;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            String message=bundle.getCharSequence("msg").toString();
            TextView textView=new TextView(getApplicationContext());
            textView.setText(message);
            listView.addView(textView);
            listView.notifyAll();
        }
    };
    ListView listView;
    int[] chatSize={0,1,1,1,0};
    ChatAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        init();
        connect();
    }
    public void init(){
        listView=(ListView) findViewById(R.id.chat_listView);
        adapter=new ChatAdapter(this,chatSize);
        listView.setStackFromBottom(true);
        listView.setAdapter(adapter);

        sendMsg=(TextView)findViewById(R.id.message);
    }
    public void connect(){
        asyncTask=new ChatAsyncTask(this);
        asyncTask.execute("192.168.219.100","4444");
    }
    public void Chat_OnClick(View view){
       finish();
    }
    public void bt_send(View view){
        if(asyncTask!=null){
            sendMsg.setText("");
            asyncTask.send(sendMsg.getText().toString());
        }
    }
}
