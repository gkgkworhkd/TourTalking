package com.example.user.myapplication.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.company.CompanyActivity;
import com.example.user.myapplication.domain.mian.Continent;
import com.example.user.myapplication.domain.mian.Country;
import com.example.user.myapplication.info.MyExpandableListAdapter;
import com.example.user.myapplication.main.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by user on 2017-02-13.
 */

public class Fragments extends android.support.v4.app.Fragment {
    String TAG;
    MainActivity context;
    ListView[] myListView = new ListView[2];
    ListViewAdapter[] listViewAdapter = new ListViewAdapter[2];
    ArrayList<Country> countries;


    public ArrayList<HashMap<String, List>> initData = new ArrayList<HashMap<String, List>>();


    public final static int ANNOUNCE = 0;
    public final static int TRV_ITEM = 1;
    // public final static int SEP_ITEM = 2;
    private int id;

    //info fragment Set
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private HashMap<String, List<String[]>> listDataChatHeaer;

    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            context.viewPager.setCurrentItem(0);
        }
    };

    public Fragments() {
        TAG = getClass().getSimpleName();


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
            initData.add(context.initAsycnTask.getAnnList());
            //context.initAsycnTask.getAnnList();
            mainInit(view);
            initData = null;
        } else if (id == R.layout.info_fragment) {
            initData.add(context.initAsycnTask.getConutryList());

            SetData();
            infoInit(view);
        } /*else if (id == R.layout.company_activity) {
            Conpany(view);
        }*/
        return view;
    }

    public void mainInit(View view) {
        myListView[0] = (ListView) view.findViewById(R.id.announce);
        myListView[1] = (ListView) view.findViewById(R.id.trv_item);
        //myListView[2] = (ListView) view.findViewById(R.id.spe_item);

        for (int i = 0; i < myListView.length; i++) {

            switch (i) {
                case ANNOUNCE:
                    listViewAdapter[i] = new ListViewAdapter(context, ANNOUNCE, initData);
                    break;
                case TRV_ITEM:
                    listViewAdapter[i] = new ListViewAdapter(context, TRV_ITEM, initData);
                    break;
               /* case SEP_ITEM:
                    listViewAdapter = new ListViewAdapter(context, SEP_ITEM);
                    break;*/
            }
            myListView[i].setAdapter(listViewAdapter[i]);

        }
    }


    public void infoInit(final View view) {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expanded_menu);
        expandableListAdapter = new MyExpandableListAdapter(context, listDataHeader, listDataChild, listDataChatHeaer, view);
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

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        context,
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                Thread thread = new Thread() {
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
                Intent intent = new Intent(context, CompanyActivity.class);
                context.startActivity(intent);


                return false;
            }

        });


    }

    //TODO INFO 단의 실제 데이터가 들어가는곳
    public void SetData() {
        Continent contiDto = null;
        Country counDto = null;

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        listDataChatHeaer = new HashMap<String, List<String[]>>();
        //임의의 데이터 그룹생성

        ArrayList<List<String>> contiList = new ArrayList<List<String>>();
        List<String> asia = new ArrayList<String>();
        List<String> northAmerica = new ArrayList<String>();
        List<String> southAmerica = new ArrayList<String>();
        List<String> oceania = new ArrayList<String>();

        contiList.add(southAmerica);
        contiList.add(northAmerica);
        contiList.add(asia);
        contiList.add(oceania);
        HashMap<String, List> list = initData.get(0);
        ArrayList<Continent> conti = (ArrayList<Continent>) list.get("continent");

        Log.d(TAG, conti + "두번쨰 열의 길이는 4여야만함");
        Log.d(TAG, conti.size() + "두번쨰 열의 길이는 4여야만함");
        countries = (ArrayList<Country>) list.get("country");
        Log.d(TAG, countries.size() + "두번쨰 열의 길이는 4여야만함");
        for (int i = 0; i < conti.size(); i++) {
            contiDto = conti.get(i);
            Log.d(TAG, contiDto + "업냐?");
            listDataHeader.add(contiDto.getContinent_name());
        }

        for (int a = 0; a < countries.size(); a++) {
            counDto = countries.get(a);

            switch (counDto.getContinent_id()) {
                case 1:
                    northAmerica.add(counDto.getCountry_name());
                    break;
                case 2:
                    southAmerica.add(counDto.getCountry_name());
                    break;
                case 3:
                    asia.add(counDto.getCountry_name());
                    Log.d(TAG, counDto.getCountry_name() + "?");
                    break;
                case 4:
                    oceania.add(counDto.getCountry_name());
                    break;
            }
        }

        for (int b = 0; b < conti.size(); b++) {
            listDataChild.put(listDataHeader.get(b), contiList.get(b));

            //listDataChatHeaer.put(listDataHeader.get(b), contiList.get(b));

        /*listDataChild.put(listDataHeader.get(0), northAmerica);
        listDataChild.put(listDataHeader.get(1), southAmerica);
        listDataChild.put(listDataHeader.get(2), asia);
        listDataChild.put(listDataHeader.get(3), oceania);*/

        }

    /*public void Conpany(View view) {
        companyListView=(ListView)view.findViewById(R.id.company);
        CompanyListAdapter adapter=new CompanyListAdapter(context);
        companyListView.setAdapter(adapter);
    }*/


    }
}
