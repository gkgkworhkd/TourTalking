package com.example.user.TourTalking.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.chat.ChatActivity;
import com.example.user.TourTalking.country_list.MyExpandableListAdapter;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.sharing.MyAppCompatActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyActivity extends MyAppCompatActivity {
    ListView companyListView;
    CompanyActivity companyActivity;
    ArrayList<Company> companies;
    String cityName;
    int city_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity);
        companyActivity = this;
        getData();
        Init();
    }

    public void getData() {
        Intent intent = getIntent();
        companies = intent.getParcelableArrayListExtra("compList");
        TextView comp_city = (TextView) findViewById(R.id.company_list_city);
        cityName = intent.getStringExtra("cityName");
        city_id = intent.getIntExtra("cityId", 0);
        comp_city.append(cityName);
        comp_city.append(" 단체 채팅");
    }

    public void Init() {
        companyListView = (ListView) findViewById(R.id.company);
        CompanyListAdapter adapter = new CompanyListAdapter(this, companies, 0);
        companyListView.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void MainBt(View view) {
        switch (view.getId()) {
            case R.id.home_toggle:
                this.finish();
                break;
        }
    }

    public void CompanyBt(View view) {
        switch (view.getId()) {
            case R.id.item_company:

                break;
        }
    }

    public void countryListBt(View view) {
        int id = view.getId();
        if (id == R.id.company_list_city) {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra(ChatActivity.GROUPTYPE, "city");
            intent.putExtra("countryName", cityName);
            intent.putExtra(ChatActivity.COUNTRYID, Integer.toString(city_id));
            intent.putExtra("protocol", MyExpandableListAdapter.getProtocol(city_id, "city"));
            startActivity(intent);
        } else if (id == R.id.company_estimate) {
            Intent intent = new Intent(this, EstimateActivity.class);
            intent.putExtra("cityName", cityName);
            startActivity(intent);
        }
    }
}
