package com.example.user.TourTalking.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.ChatList;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.domain.MemberList;

/**
 * Created by user on 2017-02-14.
 */

public class ListAdapter extends BaseAdapter {

    private ListActivity context;
    private int code;


    public ListAdapter(Context context, int code) {
        this.context = (ListActivity) context;
        this.code = code;
    }

    @Override
    public int getCount() {
        return context.arr.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return context.arr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        if (code == R.layout.chatlist_member_fragment) {
            if (position == 0) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.member_list_header, null);
                view = linearLayout;

            } else {
                Company company = (Company) context.arr.get(position - 1);
                MemberList dto = new MemberList();
                dto.setImg(company.getImage_url());
                dto.setMember_id(company.getCompany_id());
                dto.setNickName(company.getCompany_name());
                view = new MemberItem(context, dto);
                MyListViewOnItemClickListener listener=new MyListViewOnItemClickListener(context,R.layout.chatlist_member_fragment,company);
                view.setOnClickListener(listener);
            }


        } else if (code == R.layout.chatlist_list_fragment) {
            if (position == 0) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.member_list_header, null);
                TextView textView=(TextView)linearLayout.findViewById(R.id.member_header_title);
                textView.setText("");
                view = linearLayout;

            } else {
                Company company = (Company) context.arr.get(position - 1);
                ChatList dto = new ChatList();
                dto.setNickName("고재광");
                dto.setImg("이미지이름.jpg");
                dto.setContent("어제 뭐했어??");
                view = new ListItem(context, dto);
            }
        }

        return view;
    }

}
