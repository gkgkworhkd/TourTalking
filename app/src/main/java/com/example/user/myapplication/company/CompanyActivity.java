package com.example.user.myapplication.company;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.List.ListActivity;
import com.example.user.myapplication.profile.ProfileActivity;

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
        Conpany();
        companyActivity = this;

    }

    public void Conpany() {
        companyListView = (ListView) findViewById(R.id.company);
        CompanyListAdapter adapter = new CompanyListAdapter(this);
        companyListView.setAdapter(adapter);
        companyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.name_company);
                //Toast.makeText(getApplicationContext(),position+"번쨰"+textView.getText()+"이선택됨",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                companyActivity.startActivity(intent);
            }
        });
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
