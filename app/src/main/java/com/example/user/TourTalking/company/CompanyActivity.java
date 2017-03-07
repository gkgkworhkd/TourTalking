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
import com.example.user.TourTalking.profile.ProfileActivity;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyActivity extends AppCompatActivity {
    ListView companyListView;
    CompanyActivity companyActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_activity);
        Init();
        companyActivity = this;

    }

    public void Init() {
        companyListView = (ListView) findViewById(R.id.company);
        CompanyListAdapter adapter = new CompanyListAdapter(this);
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
}
