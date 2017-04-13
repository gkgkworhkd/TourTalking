package com.example.user.TourTalking.board;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.user.TourTalking.domain.Comment;
import com.example.user.TourTalking.domain.TrvBoard;
import com.example.user.TourTalking.main.NoticeItem;
import com.example.user.TourTalking.sharing.Fragments;

import java.util.ArrayList;

/**
 * Created by user on 2017-04-05.
 */

public class BoardCommentAdapter extends BaseAdapter {
    public ArrayList<Comment> list;
    BoardActivity context;
    int code;
    public BoardCommentAdapter(BoardActivity boardListActivity, ArrayList<Comment> list,int code) {
        context = boardListActivity;
        this.list = list;
        this.code=code;
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
        Comment dto = list.get(position);
        View view = new BoardCommentItem(context, dto, code);
        return view;
    }
}
