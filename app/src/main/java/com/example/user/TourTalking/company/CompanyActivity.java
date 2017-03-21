package com.example.user.TourTalking.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.chat.ChatActivity;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.profile.ProfileActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyActivity extends AppCompatActivity {
    ListView companyListView;
    CompanyActivity companyActivity;
    ArrayList<Company> companies;
    String cityName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity);
        companyActivity = this;
        getData();
        Init();
    }
    public void getData(){
        Intent intent=getIntent();
        companies=intent.getParcelableArrayListExtra("compList");
        TextView comp_city=(TextView)findViewById(R.id.company_list_city);
        cityName=intent.getStringExtra("cityName");
        comp_city.append(cityName);
        comp_city.append(" 단체 채팅");
    }

    public void Init() {
        companyListView = (ListView) findViewById(R.id.company);
        CompanyListAdapter adapter = new CompanyListAdapter(this,companies,0);
        companyListView.setAdapter(adapter);
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
    public void countryListBt(View view){
        int id=view.getId();
        if(id==R.id.company_list_city){
            startActivity(new Intent(this, ChatActivity.class));
        }else if(id==R.id.company_estimate){
            Intent intent=new Intent(this,EstimateActivity.class);
            intent.putExtra("cityName",cityName);
            startActivity(intent);
        }
    }
}
