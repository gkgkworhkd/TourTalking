package com.example.user.TourTalking.estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.company.CompanyListAdapter;
import com.example.user.TourTalking.country_list.CompanyLsitAsycnTask;
import com.example.user.TourTalking.domain.Company;

import java.util.ArrayList;

/**
 * Created by user on 2017-03-21.
 */

public class EstimateChoiceCompanyActivity extends AppCompatActivity {
    String TAG;
    ListView listView;
    CompanyListAdapter companyListAdapter;
    public ArrayList<Integer> selectCompId = new ArrayList<Integer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estimate_choicecompany_activity);
        TAG = this.getClass().getSimpleName();
        listView = (ListView) findViewById(R.id.est_choiceCompany);
        ArrayList<Company> companies = getIntent().getParcelableArrayListExtra("compList");
        companyListAdapter = new CompanyListAdapter(this, companies, EstimateActivity.CHCOMPNAY);
        listView.setAdapter(companyListAdapter);
    }

    public void estimate_choicecomp(View view) {

        for (int i = 0; i < companyListAdapter.estiCompanyItems.size(); i++) {
            if (companyListAdapter.estiCompanyItems.get(i).checkBox.isChecked()) {
                selectCompId.add(companyListAdapter.estiCompanyItems.get(i).getDto().getCompany_id());
            }
        }
        Log.d(TAG, selectCompId.size() + "선택된 아이템 게수는~/");
        startActivity(new Intent(this, EstimatePopupActivity.class));
    }
}
