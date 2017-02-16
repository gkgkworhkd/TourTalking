package com.example.user.myapplication.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.company.CompanyActivity;
import com.example.user.myapplication.company.CompanyListAdapter;
import com.example.user.myapplication.info.MyExpandableListAdapter;
import com.example.user.myapplication.main.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-13.
 */

public class Fragments extends android.support.v4.app.Fragment {
    String TAG;
    MainActivity context;
    ListView[] myListView = new ListView[3];
    ListView companyListView;
    android.os.Handler handler=new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            context.viewPager.setCurrentItem(0);
        }
    };

    public final static int ANNOUNCE = 0;
    public final static int TRV_ITEM = 1;
    public final static int SEP_ITEM = 2;
    private int id;

    //info fragment Set
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;


    public Fragments() {
        TAG = getClass().getSimpleName();
        SetData();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        id = (int) bundle.get("pageId");
        View view = inflater.inflate(id, container, false);
        context = (MainActivity) getContext();
        //TODO switch case 문으로 수정!!
        if (id == R.layout.main_fragment) {
            mainInit(view);
        } else if (id == R.layout.info_fragment) {
            infoInit(view);
        } /*else if (id == R.layout.company_activity) {
            Conpany(view);
        }*/
        return view;
    }

    public void mainInit(View view) {
        myListView[0] = (ListView) view.findViewById(R.id.announce);
        myListView[1] = (ListView) view.findViewById(R.id.trv_item);
        myListView[2] = (ListView) view.findViewById(R.id.spe_item);
        for (int i = 0; i < myListView.length; i++) {
            ListViewAdapter listViewAdapter = null;
            switch (i) {
                case ANNOUNCE:
                    listViewAdapter = new ListViewAdapter(context, ANNOUNCE);
                    break;
                case TRV_ITEM:
                    listViewAdapter = new ListViewAdapter(context, TRV_ITEM);
                    break;
                case SEP_ITEM:
                    listViewAdapter = new ListViewAdapter(context, SEP_ITEM);
                    break;
            }
            myListView[i].setAdapter(listViewAdapter);
        }
    }


    public void infoInit(final View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expanded_menu);
        expandableListAdapter = new MyExpandableListAdapter(context, listDataHeader, listDataChild, view);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        //그룹이 열릴 경우 이벤트 발생
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(context, listDataHeader.get(groupPosition) + "EXPANDED", Toast.LENGTH_SHORT).show();
                Thread thread=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();
                Intent intent=new Intent(context,CompanyActivity.class);
                context.startActivity(intent);

            }
        });
        //그룹이 닫힐경우 이벤트 발생
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(context, listDataHeader.get(groupPosition) + "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        //차일드 뷰를 눌럿을 경우 이벤트 발생
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            //TODO CHILDVIEW 가 안눌림
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(context, listDataHeader.get(groupPosition) + ":" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                /*Thread thread=new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        try {
                            Thread.sleep(2000);
                            handler.sendEmptyMessage(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                };
                thread.start();
                Intent intent=new Intent(context,CompanyActivity.class);
                context.startActivity(intent);*/
                return false;
            }
        });
    }

    //TODO INFO 단의 실제 데이터가 들어가는곳
    public void SetData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        //임의의 데이터 그룹생성
        listDataHeader.add("아시아");
        listDataHeader.add("북아메리카");
        listDataHeader.add("남아메리카");
        listDataHeader.add("오세아니아");

        List<String> asia = new ArrayList<String>();
        asia.add("대만");
        asia.add("마카오");
        asia.add("인도네시아");
        asia.add("일본");
        asia.add("중국");
        asia.add("태국");
        asia.add("말레이시아");
        asia.add("배트남");

        List<String> northAmerica = new ArrayList<String>();
        northAmerica.add("멕시코");
        northAmerica.add("캐나다");
        northAmerica.add("자메이카");
        northAmerica.add("온두라스");
        northAmerica.add("쿠바");

        List<String> southAmerica = new ArrayList<String>();
        southAmerica.add("에콰도르");
        southAmerica.add("아르헨티나");
        southAmerica.add("베네수엘라");
        southAmerica.add("우루과이");
        southAmerica.add("칠레");
        List<String> oceania = new ArrayList<String>();
        oceania.add("호주");
        oceania.add("뉴질랜드");
        oceania.add("인도네시아");
        oceania.add("바누아투");
        oceania.add("괌(미국)");
        oceania.add("팔라우(미국)");

        listDataChild.put(listDataHeader.get(2), asia);
        listDataChild.put(listDataHeader.get(0), northAmerica);
        listDataChild.put(listDataHeader.get(1), southAmerica);
        listDataChild.put(listDataHeader.get(3), oceania);

    }

    /*public void Conpany(View view) {
        companyListView=(ListView)view.findViewById(R.id.company);
        CompanyListAdapter adapter=new CompanyListAdapter(context);
        companyListView.setAdapter(adapter);
    }*/


}
