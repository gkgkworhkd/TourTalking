package com.example.user.TourTalking.country_list;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.chat.ChatActivity;
import com.example.user.TourTalking.domain.City;
import com.example.user.TourTalking.domain.Country;
import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.estimate.EstimateCountryListActivity;
import com.example.user.TourTalking.sharing.ImageAsycnTask;
import com.example.user.TourTalking.sharing.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-13.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private List<String> _listDataHeader;
    //CountryDTO 로 변경
    private HashMap<String, List<Country>> _countryListData;
    //CITYDTO러ㅗ
    private HashMap<String, List<String>> _listDataChat;
    String countryName;
    HashMap<String, List<ArrayList<City>>> _cityListData;
    private ViewHolder viewHolder;
    private View _view;
    private String TAG;

    public MyExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, List<Country>> _countryListData, HashMap<String, List<String>> listDataChat, View view, HashMap<String, List<ArrayList<City>>> _cityListData
    ) {
        TAG = this.getClass().getSimpleName();
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._countryListData = _countryListData;
        this._view = view;
        this._listDataChat = listDataChat;
        this._cityListData = _cityListData;
    }

    //차일드 뷰 반환

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._countryListData.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    public Object getCityChild(int groupPosition, int childPosititon) {
        return this._cityListData.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }


    public List myGetChild(int groupPosition) {
        return this._countryListData.get(this._listDataHeader.get(groupPosition));
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

        final Country country = (Country) getChild(groupPosition, childPosition);
        final ArrayList<City> city = (ArrayList<City>) getCityChild(groupPosition, childPosition);
        //final List childCityText = myGetChild(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.country_list_item, null);
        }


        LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.libListItem);
        TextView chat_name = (TextView) convertView.findViewById(R.id.country_chat_name);
        ImageView country_img = (ImageView) convertView.findViewById(R.id.country_img);
        ImageAsycnTask imageAsycnTask = new ImageAsycnTask(country_img, 50);

        countryName = country.getCountry_name();
        chat_name.setText(countryName);
        chat_name.append(" 단체 채팅방");
        chat_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.mainActivity.memberInfo[0] == null) {
                    //TODO 비로그인시 알림창 활성화
                    Log.d(TAG, "로그인 후 사용하실수있는 메뉴입니다.");
                } else {
                    Intent intent = new Intent(_context, ChatActivity.class);
                    Log.d(TAG, country.getCountry_id() + "선택된 도시의 아이디");
                    intent.putExtra(ChatActivity.COUNTRYID, Integer.toString(country.getCountry_id()));
                    intent.putExtra(ChatActivity.GROUPTYPE, "country");
                    intent.putExtra("protocol",getProtocol(country));
                    //TODO 로그인된 맴버 PK 전송
                    _context.startActivity(intent);
                }
            }
        });
        //String[] cityName = (String[]) childText[1]);\
        linearLayout.removeAllViews();
        if (city.size() != 0) {
            for (int i = 0; i < city.size(); i++) {
                final String cityName = city.get(i).getCity_name();
                linearLayout.addView(getCityListLayout(cityName));
            }
        } else {
            //txtListChild.setText(childText);
            //linearLayout.addView(getCityListLayout(country.getCountry_name()));
            linearLayout.addView(getCityListLayout(" "));
           /* chat_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(_context, ChatActivity.class);
                    intent.putExtra("counti_id", chat_id);
                    intent.putExtra("groupType","country");
                    _context.startActivity(intent);
                }
            });*/
        }
        imageAsycnTask.execute(country.getCountry_image());
        return convertView;
    }

    public String getProtocol(Country country) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"chat-type\":\"group\",");
        sb.append("\"group-type\":\"" + "country" + "\",");
        sb.append("\"group-id\":" + Integer.toString(country.getCountry_id()) + ",");
        sb.append("\"member-type\":\"" + MainActivity.mainActivity.memberInfo[0] + "\",");
        sb.append("\"member-id\":\"" + MainActivity.mainActivity.memberInfo[1] + "\"");
        sb.append("}");
        return sb.toString();
    }


    public CityListLayout getCityListLayout(final String text) {
        CityListLayout listLayout = new CityListLayout(_context);
        listLayout.textView.setText(text);
        listLayout.textView.append(" ");

        /*
        도시이름 나누기
        listLayout.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));
        */
        listLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));

        listLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(_context, text + " 를 눌럿다", Toast.LENGTH_SHORT).show();
                if (_context.equals(MainActivity.mainActivity)) {
                    CompanyLsitAsycnTask companyLsitAsycnTask = new CompanyLsitAsycnTask(_context, 0);
                    companyLsitAsycnTask.execute("http://192.168.219.101:7777/device/compList?city_name=", "GET", text);
                } else if (_context.equals(EstimateCountryListActivity.estimateCountryListActivity)) {
                    _context.startActivity(new Intent(_context, EstimateActivity.class).putExtra("countName", countryName + " " + text));
                }
            }
        });
        return listLayout;
    }

    //차일드 뷰의 사이즈 반환
    @Override
    public int getChildrenCount(int groupPosition) {
        return this._countryListData.get(this._listDataHeader.get(groupPosition))
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
            convertView = infalInflater.inflate(R.layout.country_list_list_group, null);
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            viewHolder.iv_image.setBackgroundColor(Color.parseColor("#eaeaea"));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //그룹을 펼칠 때 또는 닫을 때 아이콘 변경
        if (isExpanded) {
            viewHolder.iv_image.setImageResource(R.drawable.arrow_up);
        } else {
            viewHolder.iv_image.setImageResource(R.drawable.arrow_down);
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
