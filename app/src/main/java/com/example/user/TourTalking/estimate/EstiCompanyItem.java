package com.example.user.TourTalking.estimate;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

/**
 * Created by user on 2017-03-21.
 */

public class EstiCompanyItem extends LinearLayout{
    private Company dto;
    public  CheckBox checkBox;
    View view;
    public EstiCompanyItem(Context context,Company dto) {
        super(context);
        this.dto=dto;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.estimate_company_info_item, this);
        setData();
    }

    public void setData() {
        TextView compIntro=(TextView)view.findViewById(R.id.intro_company);
        TextView compName=(TextView) view.findViewById(R.id.name_company);
        ImageView compImage=(ImageView)view.findViewById(R.id.image_company);
        checkBox=(CheckBox)findViewById(R.id.est_check);
        compIntro.setText(dto.getCompany_intro());
        compName.setText(dto.getCompany_name());
        ImageAsycnTask imageAsycnTask=new ImageAsycnTask(compImage,270);
        imageAsycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,dto.getImage_url(),"GET");
    }

    public Company getDto() {
        return dto;
    }

    public void setDto(Company dto) {
        this.dto = dto;
    }
}
