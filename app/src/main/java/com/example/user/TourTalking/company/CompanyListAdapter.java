package com.example.user.TourTalking.company;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.estimate.EstiCompanyItem;
import com.example.user.TourTalking.estimate.EstimateActivity;
import com.example.user.TourTalking.estimate.EstimateChoiceCompanyActivity;
import com.example.user.TourTalking.profile.ProfileActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Company> companies;
    String TAG;
    int type;
    View companyItem;
    public ArrayList<EstiCompanyItem> estiCompanyItems=new ArrayList<EstiCompanyItem>();
    public CompanyListAdapter(Context context, ArrayList<Company> companies, int type) {
        this.context = context;
        TAG = this.getClass().getSimpleName();
        this.companies = companies;
        this.type = type;
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
        View view = null;

        final Company dto = companies.get(position);
        if (convertView == null) {
            if (type == EstimateActivity.CHCOMPNAY) {
                final EstiCompanyItem item = new EstiCompanyItem(context, dto);
                final EstimateChoiceCompanyActivity ch=(EstimateChoiceCompanyActivity) context;
                item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(item.checkBox.isChecked()){
                            item.checkBox.setChecked(false);
                        }else {
                            item.checkBox.setChecked(true);
                        }
                    }
                });
                estiCompanyItems.add(item);
                companyItem =item;
            } else {
                companyItem = new CompanyItem(context, dto);
                companyItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, ProfileActivity.class);
                        intent.putExtra("compData", dto);
                        intent.putExtra("country_id", 444);
                        context.startActivity(intent);
                    }
                });
            }
            view = companyItem;
        } else {

            if(type == EstimateActivity.CHCOMPNAY){
                EstiCompanyItem item= (EstiCompanyItem) view;
                item.setDto(dto);
            }else {
                CompanyItem item = (CompanyItem) view;
                item.setDto(dto);
            }

            convertView = view;
        }


        return view;
    }
}
