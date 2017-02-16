package com.example.user.myapplication.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-14.
 */

public class ListFragments extends Fragment {
    private int id;
    private String TAG;
    ListView chat_listView;
    ListActivity context;
    MyListViewOnItemClickListener listener;
    public ListFragments() {
        TAG = this.getClass().getSimpleName();
        Log.d(TAG, "ListFragments 가 실행됨");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "ListFragments의 OnCreateView가 실행됨");
        Bundle bundle = getArguments();
        id = (int) bundle.get("c_pageId");
        Log.d(TAG, "id 는?" + id);
        View view = inflater.inflate(id, container, false);
        context = (ListActivity) getContext();
        if (id == R.layout.chatlist_member_fragment) {
            chat_listView = (ListView) view.findViewById(R.id.listView_friendList);
        } else if (id == R.layout.chatlist_list_fragment) {
            chat_listView = (ListView) view.findViewById(R.id.listView_chatList);
        }
        ListAdapter listAdapter = new ListAdapter(context, id);
        listener=new MyListViewOnItemClickListener(context,id);
        chat_listView.setOnItemClickListener(listener);
        chat_listView.setAdapter(listAdapter);
        return view;
    }
}
