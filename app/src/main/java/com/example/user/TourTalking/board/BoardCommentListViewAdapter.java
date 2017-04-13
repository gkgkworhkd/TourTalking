package com.example.user.TourTalking.board;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.TourTalking.domain.TrvBoard;
import com.example.user.TourTalking.main.NoticeItem;
import com.example.user.TourTalking.sharing.Fragments;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-14.
 */

public class BoardCommentListViewAdapter extends BaseAdapter {
    ArrayList<TrvBoard> list;
    BoardActivity context;
    private String TAG;

    public BoardCommentListViewAdapter(BoardActivity boardListActivity, ArrayList<TrvBoard> list) {
        context = boardListActivity;
        this.list = list;
        TAG=this.getClass().getSimpleName();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        TrvBoard dto = list.get(position);
        Log.d(TAG,dto.getCompany_name()+"뷰의 이름");
        view = new NoticeItem(context, dto, Fragments.TRV_ITEM);
        if (convertView != null) {
            convertView = view;
        }
        return view;
    }
}
