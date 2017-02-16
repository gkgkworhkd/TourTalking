package com.example.user.myapplication.info;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-13.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter{
    private Context _context;
    private List<String> _listDataHeader;
    private HashMap<String,List<String>> _listDataChild;
    private ViewHolder viewHolder;
    private View _view;
    private String TAG;

    public MyExpandableListAdapter(Context context, List<String> listDataHeader,
                                   HashMap<String, List<String>> listChildData,View view) {
        TAG=this.getClass().getSimpleName();
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this._view=view;
    }

    //return childView
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).get(childPosition);
    }

    //return childView id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public int getGroupCount() {
        return _listDataHeader.size();
    }

    //return childView size
    @Override
    public int getChildrenCount(int groupPosition) {
        return _listDataChild.get(_listDataHeader.get(groupPosition)).size();
    }

    //return position for group
    @Override
    public Object getGroup(int groupPosition) {
        return _listDataHeader.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle=(String)getGroup(groupPosition);
        if(convertView==null){
           // viewHolder=new ViewHolder(_view);
            LayoutInflater inflater=(LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.info_list_group,null);
            //convertView.setTag(viewHolder);
        }else{
            //viewHolder=(ViewHolder)convertView.getTag();
        }

        if(isExpanded){
           //viewHolder.iv_image.setBackgroundColor(Color.GREEN);
        }else{
           // viewHolder.iv_image.setBackgroundColor(Color.WHITE);
        }
        TextView lblListHeader=(TextView)convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childText=(String)getChild(groupPosition, childPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.info_list_item,null);
        }
        TextView txtListChild=(TextView)convertView.findViewById(R.id.libListItem);
        //Log.d(TAG,txtListChild+"생성");
        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class ViewHolder{
        View view;
        public ViewHolder(View view){
            this.view=view;
        }
        public ImageView iv_image=(ImageView) view.findViewById(R.id.iv_image);
    }

}
