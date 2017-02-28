package com.example.user.TourTalking.info;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.TourTalking.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-13.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String, List<String>> _listDataChild;
    private HashMap<String, List<String>> _listDataChat;
    HashMap<String, List<String[]>> _listDataChatHeder;
    private ViewHolder viewHolder;
    private View _view;
    private String TAG;

    public MyExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<String>> listChildData, HashMap<String, List<String>> listDataChat, View view,HashMap<String, List<String[]>> listDataChatHeder) {
        TAG = this.getClass().getSimpleName();
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._view = view;
        this._listDataChat = listDataChat;
        this._listDataChatHeder=listDataChatHeder;
    }

    //차일드 뷰 반환

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }
    public Object getCityChild(int groupPosition, int childPosititon) {
        return this._listDataChatHeder.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    public List myGetChild(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    //차일드 뷰 ID 반환
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //차일드 뷰 생성(각 차일드 뷰의 (ROW)
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        final String[] cityTexts=(String[])getCityChild(groupPosition, childPosition);
        //final List childCityText = myGetChild(groupPosition);
        if (convertView == null) {

            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.info_list_item, null);
        }

        TextView txtListChild = (TextView) convertView.findViewById(R.id.libListItem);
        TextView chat_name = (TextView) convertView.findViewById(R.id.country_chat_name);
        TextView country_img = (TextView) convertView.findViewById(R.id.country_img);

        txtListChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_context,childText+"눌럿다",Toast.LENGTH_SHORT).show();
            }
        });

        chat_name.setText(childText);

        chat_name.append(" 단체 채팅방");

        country_img.setText(childText);
        //String[] cityName = (String[]) childText[1]);
    if(cityTexts.length!=0){
        txtListChild.setText("");
        for (int i = 0; i < cityTexts.length; i++) {
            txtListChild.append(cityTexts[i]);
            txtListChild.append(" ");
        }
    }else {
        txtListChild.setText(childText);
    }

        return convertView;
    }

    //차일드 뷰의 사이즈 반환
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    //그룹 포지션 반환
    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    //그룹 사이즈를 반환
    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    //그룹 ID를 반환
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //그룹 뷰 생성(그룹 각 뷰의 ROW)
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.info_list_group, null);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //그룹을 펼칠 때 또는 닫을 때 아이콘 변경
        if (isExpanded) {
            viewHolder.iv_image.setBackgroundColor(Color.GREEN);
        } else {
            viewHolder.iv_image.setBackgroundColor(Color.WHITE);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    //뷰홀더 클래스 생
    class ViewHolder {
        public ImageView iv_image;
    }

}
