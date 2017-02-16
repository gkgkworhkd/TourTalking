package com.example.user.myapplication.List;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.chat.ChatActivity;
import com.example.user.myapplication.profile.ProfileActivity;

/**
 * Created by user on 2017-02-16.
 */

public class MyListViewOnItemClickListener implements AdapterView.OnItemClickListener {
    int pageId;
    ListActivity listActivity;
    Intent intent;
    public MyListViewOnItemClickListener(ListActivity listActivity,int pageId) {
        this.pageId = pageId;
        this.listActivity=listActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (pageId == R.layout.chatlist_member_fragment) {
            //Toast.makeText(listActivity,position+"번째가 눌렸다",Toast.LENGTH_SHORT).show();
            intent=new Intent(listActivity, ProfileActivity.class);
        } else if (pageId == R.layout.chatlist_list_fragment) {
            //Toast.makeText(listActivity,"chatlist "+position+"번째가 눌렸다",Toast.LENGTH_SHORT).show();
            intent=new Intent(listActivity, ChatActivity.class);
        }
        listActivity.startActivity(intent);
    }
}
