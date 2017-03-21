package com.example.user.TourTalking.board;

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

public class BoardListViewAdapter extends BaseAdapter {
    ArrayList<TrvBoard> list;
    BoardListActivity context;

    public BoardListViewAdapter(BoardListActivity boardListActivity) {
        context = boardListActivity;
        list = context.asycnTask.trvList;
    }

    @Override
    public int getCount() {
        return list.size();
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
        TrvBoard dto = list.get(position);

        if(convertView==null){
            view = new NoticeItem(context, dto, Fragments.TRV_ITEM);
        }else {
            NoticeItem noticeItem=(NoticeItem) view;
            noticeItem.setDto(dto);
            noticeItem.setCode(Fragments.TRV_ITEM);
            convertView=view;
        }


        return view;
    }
}
