package com.example.user.TourTalking.company;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.company.Company;
import com.example.user.TourTalking.profile.ProfileActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyListAdapter extends BaseAdapter{
    CompanyActivity context;
    ArrayList<Company> companies;
    String TAG;
    public CompanyListAdapter(Context context){
    this.context=(CompanyActivity) context;
        getData();
        TAG=this.getClass().getSimpleName();
    }
    public void getData(){
        Intent intent=context.getIntent();
        companies=intent.getParcelableArrayListExtra("compList");
        TextView comp_city=(TextView)context.findViewById(R.id.company_list_city);
        comp_city.append(intent.getStringExtra("cityName"));
    }
    @Override
    public int getCount() {
        return companies.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=null;
        final Company dto=companies.get(position);
        CompanyItem companyItem=new CompanyItem(context,dto);
        companyItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileActivity.class);
                intent.putExtra("compData",dto);
                context.startActivity(intent);
            }
        });
        view=companyItem;
        return view;
    }
}
