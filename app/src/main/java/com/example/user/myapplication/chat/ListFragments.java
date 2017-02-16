package com.example.user.myapplication.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.myapplication.R;

/**
 * Created by user on 2017-02-14.
 */

public class ListFragments extends Fragment {
    private int id;
    private String TAG;

    public ListFragments() {
        TAG = this.getClass().getSimpleName();
        Log.d(TAG,"ListFragments 가 실행됨");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG,"ListFragments의 OnCreateView가 실행됨");
        Bundle bundle = getArguments();
        id = (int) bundle.get("c_pageId");
        Log.d(TAG, "id 는?" + id);
        View view = inflater.inflate(id, container, false);

        if (id == R.layout.chat_list_fragment) {
            setFriendList();
        } else if (id == R.layout.chat_member_fragment) {
            setChatList();
        }
        return view;
    }


    public void setLayout() {

    }

    public void setFriendList() {
        setLayout();
    }

    public void setChatList() {
        setLayout();
    }

}
