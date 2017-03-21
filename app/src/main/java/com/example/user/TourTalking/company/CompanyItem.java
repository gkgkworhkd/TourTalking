package com.example.user.TourTalking.company;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

/**
 * Created by user on 2017-02-14.
 */

public class CompanyItem extends LinearLayout {
    private Company dto;
    View view;
    public CompanyItem(Context context,Company dto) {
        super(context);
        this.dto=dto;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.company_info_item, this);
        setData();
    }

    public void setData() {
        TextView compIntro=(TextView)view.findViewById(R.id.intro_company);
        TextView compName=(TextView) view.findViewById(R.id.name_company);
        ImageView compImage=(ImageView)view.findViewById(R.id.image_company);
        compIntro.setText(dto.getCompany_intro());
        compName.setText(dto.getCompany_name());
        ImageAsycnTask imageAsycnTask=new ImageAsycnTask(compImage,270);
        imageAsycnTask.execute(dto.getImage_url(),"GET");
    }

    public Company getDto() {
        return dto;
    }

    public void setDto(Company dto) {
        this.dto = dto;
    }
}
