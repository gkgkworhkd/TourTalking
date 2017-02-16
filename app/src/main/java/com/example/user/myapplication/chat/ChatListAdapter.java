package com.example.user.myapplication.chat;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by user on 2017-02-14.
 */

public class ChatListAdapter extends BaseAdapter {

    Context context;
    int code;
    public ChatListAdapter(Context context,int code) {
        this.context = context;
        this.code=code;
    }

    @Override
    public int getCount() {
        return 8;
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
        View view = null;
        if(code==ChatFragmentAdapter.CHFRGID [0]){
            view=new ChatItem(context);
        }else if(code==ChatFragmentAdapter.CHFRGID [1]){
            view=new ChatItem(context);
        }
        return view;
    }

}
