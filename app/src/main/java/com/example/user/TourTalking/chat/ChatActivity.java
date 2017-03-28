package com.example.user.TourTalking.chat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.sharing.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-16.
 */

public class ChatActivity extends AppCompatActivity {
    private String chatType;
    private ChatAsyncTask asyncTask;
    private TextView sendMsg;
    private String TAG;
    private Intent intent;
    private Parcelable dto;
    private String groupType;
    private int group_id;
    /*
    * --------------------------상수
    * */
    public static final int SENDMESSAGE = 0;
    public static final int REVMESSAGE = 1;
    public static final String GRUOPCHAT = "groupchat";
    public static final String INDIVICHAT = "indivichat";
    public static final String COUNTRYID = "country_id";
    public static final String GROUPTYPE = "groupType";

    String protocol;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handlerOperation");
            Bundle bundle = msg.getData();
            String message = bundle.getCharSequence("msg").toString();
            Log.d(TAG, "message : " + message);
            try {
                JSONObject obj = new JSONObject(message);
                if (obj.getString("member-id").equals(MainActivity.mainActivity.memberInfo[1])) {
                    adapter.chatSize.add(getSendMessage(obj.getString("message")));
                }else {
                    String[] receviMesg={obj.getString("member-id"),obj.getString("message")};
                    adapter.chatSize.add(getReceiveMessage(receviMesg));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


    };
    ListView listView;
    ArrayList chatSize = new ArrayList();
    ChatAdapter adapter;

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        TAG = this.getClass().getSimpleName();
        init();
        connect();


    }

    public void reqEstimate(View view) {
        Intent intent = new Intent(this, EstimateActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        asyncTask.msg = "close";
        finish();
    }

    public void init() {
        listView = (ListView) findViewById(R.id.chat_listView);
        adapter = new ChatAdapter(this, chatSize);
        listView.setStackFromBottom(true);
        listView.setAdapter(adapter);
        sendMsg = (TextView) findViewById(R.id.message);
        parseIntent(getIntent());
    }

    public void connect() {
        asyncTask = new ChatAsyncTask(this);
        asyncTask.execute("192.168.219.101", "8888", protocol);
    }

    public void parseIntent(Intent intent) {
        Log.d(TAG, "parseIntent 활성화");
        if (MainActivity.mainActivity.memberInfo[0] != null) {
            if ((groupType = intent.getStringExtra(GROUPTYPE)) != null) {
                group_id = Integer.parseInt(intent.getStringExtra(COUNTRYID));
                protocol = intent.getStringExtra("protocol");
                Toolbar toolbar = (Toolbar) findViewById(R.id.chat_toolbar);
                TextView toolText = (TextView) toolbar.findViewById(R.id.chat_toolbar_reqtext);
                toolText.setText("");
                setSupportActionBar(toolbar);
                chatType = GRUOPCHAT;
                Log.d(TAG, "그룹채팅 활성화");
                Log.d(TAG, protocol);

            } else if ((dto = intent.getParcelableExtra(INDIVICHAT)) != null) {
                Toolbar toolbar = (Toolbar) findViewById(R.id.chat_toolbar);
                setSupportActionBar(toolbar);
                chatType = INDIVICHAT;
                Log.d(TAG, "개인채팅 활성화");
            }
        }
    }

    public void bt_send(View view) {

        /*if (asyncTask != null && chatType == GRUOPCHAT) {
            //asyncTask.msg = getGroupProtocol(sendMsg.getText().toString());
           // Log.d(TAG, getGroupProtocol(sendMsg.getText().toString()));
           // asyncTask.msg = getGroupProtocol(sendMsg.getText().toString());
            asyncTask.msg=sendMsg.getText().toString();
            sendMsg.setText("");

        } else if (asyncTask != null && chatType == INDIVICHAT) {
           // asyncTask.msg = getIndividualProtocol(sendMsg.getText().toString());
            asyncTask.msg=sendMsg.getText().toString();
            sendMsg.setText("");
        }*/

        asyncTask.msg = sendMsg.getText().toString();
        sendMsg.setText("");
    }

    public SendMessage getSendMessage(String msg) {
        SendMessage item = new SendMessage(this, msg);
        return item;
    }
    public ReceiveMessage getReceiveMessage(String[] msg){
        ReceiveMessage item=new ReceiveMessage(this,msg);
        return item;
    }

    private String getGroupProtocol(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"requestHeader\":{");
        sb.append("\"chat-type\":\"group\",");
        sb.append("\"group-type\":\"" + groupType + "\",");
        sb.append("\"group-id\":" + group_id + ",");
        sb.append("\"member-type\":\"" + MainActivity.mainActivity.memberInfo[0] + "\",");
        sb.append("\"member-id\":\"" + MainActivity.mainActivity.memberInfo[1] + "\"");
        sb.append("},");
        sb.append("\"message\":\"" + msg + "\"");
        sb.append("}");
        return sb.toString();

    }


    private String getIndividualProtocol(String msg) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"requestHeader\":{");
        sb.append("\"chat-type\":\"individual\",");
        sb.append("\"target-type\":\"agency\",");
        sb.append("\"target-id\":2,");
        sb.append("\"member-type\":\"" + MainActivity.mainActivity.memberInfo[0] + "\",");
        sb.append("\"member-id\":" + Integer.parseInt(MainActivity.mainActivity.memberInfo[1]) + "");
        sb.append("},");
        sb.append("\"message\":\"" + msg + "\"");
        sb.append("}");
        return sb.toString();
    }


}
