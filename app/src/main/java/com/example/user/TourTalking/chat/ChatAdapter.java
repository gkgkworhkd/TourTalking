package com.example.user.TourTalking.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-16.
 */

public class ChatAdapter extends BaseAdapter {
    Context context;
    ArrayList chatSize;
    int sum;
    public ChatAdapter(Context context, ArrayList chatSize) {
        this.context = context;
        this.chatSize=chatSize;
    }

    @Override
    public int getCount() {
        return chatSize.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = (View) chatSize.get(position);

        /*if (chatSize[sum] == ChatActivity.REVMESSAGE) {
            String[] recvMsg=new String[2];
            view=new ReceiveMessage(context,recvMsg);
        } else if (chatSize[sum]== ChatActivity.SENDMESSAGE) {
            String sendMsg="보낼 메세지";
            view=new SendMessage(context,sendMsg);
        }
        if(sum<chatSize.length)sum++;*/
        return view;
    }
}
