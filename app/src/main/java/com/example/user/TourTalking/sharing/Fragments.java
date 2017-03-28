package com.example.user.TourTalking.sharing;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.City;
import com.example.user.TourTalking.domain.Continent;
import com.example.user.TourTalking.domain.Country;
import com.example.user.TourTalking.country_list.MyExpandableListAdapter;
import com.example.user.TourTalking.estimate.EstimateCountryListActivity;
import com.example.user.TourTalking.main.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2017-02-13.
 */

public class Fragments extends android.support.v4.app.Fragment {
    String TAG;
    MainActivity context;
    EstimateCountryListActivity activity;
    ListView[] myListView = new ListView[1];
    ListViewAdapter[] listViewAdapter = new ListViewAdapter[1];
    ArrayList<Country> countries;
    ArrayList<List<String[]>> cityList;
    public ArrayList<HashMap<String, List>> initData = new ArrayList<HashMap<String, List>>();
    public final static int ANNOUNCE = 0;
    public final static int TRV_ITEM = 1;
    // public final static int SEP_ITEM = 2;
    private int id;
    //info fragment Set
    private ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private List<String> listDataHeader;
    private HashMap<String, List<Country>> listDataChild;
    private HashMap<String, List<ArrayList<City>>> listChatChild;
    private HashMap<String, List<String>> listDataChatHeaer;
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

        //TODO switch case 문으로 수정!!
        if (id == R.layout.main_fragment) {
            context = (MainActivity) getContext();
            initData.add(context.initAsycnTask.getAnnList());
            //context.initAsycnTask.getAnnList();
            mainInit(view);

            initData = null;
        } else if (id == R.layout.country_list_fragment) {
            context = (MainActivity) getContext();
            initData.add(context.initAsycnTask.getConutryList());
            SetData();
            infoInit(view, R.id.expanded_menu, context);
        } else if (id == R.layout.estimate_complist_fragment) {
            activity = (EstimateCountryListActivity) getContext();
            initData.add(activity.initAsycnTask.getConutryList());
            Log.d(TAG, activity.initAsycnTask.getConutryList() + "견적서 데이터 가 있다");
            SetData();
            infoInit(view, R.id.expanded_menu_estmate, activity);
        }
        return view;
    }

    public void mainInit(View view) {
        //myListView[0] = (ListView) view.findViewById(R.id.announce);
        myListView[0] = (ListView) view.findViewById(R.id.trv_item);
        //myListView[2] = (ListView) view.findViewById(R.id.spe_item);
       /* for (int i = 0; i < myListView.length; i++) {

            switch (i) {
                case ANNOUNCE:
                    listViewAdapter[i] = new ListViewAdapter(context, ANNOUNCE, initData);
                    break;
                case TRV_ITEM:
                    listViewAdapter[i] = new ListViewAdapter(context, TRV_ITEM, initData);
                    break;
               *//* case SEP_ITEM:
                    listViewAdapter = new ListViewAdapter(context, SEP_ITEM);
                    break;*//*
            }
            myListView[i].setAdapter(listViewAdapter[i]);

        }*/
        listViewAdapter[0] = new ListViewAdapter(context, TRV_ITEM, initData);
        myListView[0].setAdapter(listViewAdapter[0]);
    }

    public void infoInit(final View view, int id, final Context context) {
        expandableListView = (ExpandableListView) view.findViewById(id);
        expandableListAdapter = new MyExpandableListAdapter(context, listDataHeader, listDataChild, listDataChatHeaer, view, listChatChild);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.expandGroup(2);
        expandableListView.smoothScrollToPosition(expandableListAdapter.getGroupCount());


        //그룹이 열릴 경우 이벤트 발생
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0; i < expandableListAdapter.getGroupCount(); i++) {
                    if (i != groupPosition) {
                        expandableListView.collapseGroup(i);
                    }
                }
            }
        });
        //그룹이 닫힐경우 이벤트 발생
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });
        //차일드 뷰를 눌럿을 경우 이벤트 발생
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub

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
                return false;
            }

        });
    }

    //TODO INFO 단의 실제 데이터가 들어가는곳
    public void SetData() {
        Continent contiDto = null;
        Country counDto = null;

        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Country>>();
        listDataChatHeaer = new HashMap<String, List<String>>();
        listChatChild = new HashMap<String, List<ArrayList<City>>>();
        //임의의 데이터 그룹생성

        ArrayList<List> contiList = new ArrayList<List>();
        cityList = new ArrayList<List<String[]>>();
        /*List asia = new ArrayList();
        List northAmerica = new ArrayList();
        List southAmerica = new ArrayList();
        List oceania = new ArrayList();

        List asia1 = new ArrayList();
        List northAmerica1 = new ArrayList();
        List southAmerica1 = new ArrayList();
        List oceania1 = new ArrayList();*/


        List<String[]> cityNames = null;

        HashMap<String, List> list = initData.get(0);
        ArrayList<Continent> conti = (ArrayList<Continent>) list.get("continent");
        HashMap<Integer, ArrayList<Country>> country = new HashMap<Integer, ArrayList<Country>>();
        HashMap<Integer, ArrayList<ArrayList<City>>> city = new HashMap<Integer, ArrayList<ArrayList<City>>>();
        countries = (ArrayList<Country>) list.get("country");

        for (int i = 0; i < conti.size(); i++) {
            contiDto = conti.get(i);
            int countiId = contiDto.getContinent_id();
            listDataHeader.add(contiDto.getContinent_name());

            ArrayList<Country> con = new ArrayList();
            ArrayList<ArrayList<City>> con1 = new ArrayList();


            country.put(countiId, con);
            city.put(countiId, con1);
        }
        for (int a = 0; a < countries.size(); a++) {
            counDto = countries.get(a);
            int countId = counDto.getContinent_id();
            //5개으 conti 가있다.
            ArrayList<Country> con = country.get(countId);
            ArrayList<ArrayList<City>> con1 = city.get(countId);
            if (counDto.getCountry_name() != null) {
                con.add(counDto);
                con1.add((ArrayList<City>) counDto.getCity());
            }
            //contiList.add(con);
            //cityList.add(con1);

        }


            /*for(int i=0;i<continent.size();i++){
                    //continent.get(counDto.getContinent_id()).get(counDto.getContinent_id()-1).add(counDto.getCountry_name());
                    //continent1.get(counDto.getContinent_id()).get(counDto.getContinent_id()-1).add(getCityNames(counDto));
                }*/


        //continent.get(counDto.getContinent_id()).get(counDto.getContinent_id()-1).add(counDto.getCountry_name());
        //continent1.get(counDto.getContinent_id()).get(counDto.getContinent_id()-1).add(getCityNames(counDto));

            /*switch (counDto.getContinent_id()) {

                case 1:
                    northAmerica.add(counDto.getCountry_name());
                    northAmerica1.add(getCityNames(counDto));
                    break;
                case 2:
                    southAmerica.add(counDto.getCountry_name());
                    southAmerica1.add(getCityNames(counDto));
                    break;
                case 3:
                    asia.add(counDto.getCountry_name());
                    asia1.add(getCityNames(counDto));
                    break;
                case 4:
                    oceania.add(counDto.getCountry_name());
                    oceania1.add(getCityNames(counDto));
                    break;
            }*/

       /* contiList.add(southAmerica);
        contiList.add(northAmerica);
        contiList.add(asia);
        contiList.add(oceania);

        cityList.add(southAmerica1);
        cityList.add(northAmerica1);
        cityList.add(asia1);
        cityList.add(oceania1);*/


        // /reSetData(northAmerica,counDto);
        // reSetData(southAmerica,counDto);
        //reSetData(asia,counDto);
        // reSetData(oceania,counDto);

        for (int b = 0; b < conti.size(); b++) {
            contiDto = conti.get(b);
            listDataChild.put(conti.get(b).getContinent_name(), country.get(contiDto.getContinent_id()));
            listChatChild.put(conti.get(b).getContinent_name(), city.get(contiDto.getContinent_id()));
            // listDataChild.put(listDataHeader.get(b), cityList.get(b));
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

    public void reSetData(List list, Country counDto) {
        list.clear();
        list.add(getCityNames(counDto));
        cityList.add(list);
    }

    public String[] getCityNames(Country counDto) {
        ArrayList<City> cityArrList = (ArrayList<City>) counDto.getCity();
        String[] cityList = new String[cityArrList.size()];
        for (int i = 0; i < cityArrList.size(); i++) {
            City city = cityArrList.get(i);
            cityList[i] = city.getCity_name();
        }
        return cityList;
    }
}
