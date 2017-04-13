package com.example.user.TourTalking.company;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.os.AsyncTask;
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
    private ImageView bookMark;
    View view;
    private AlertDialog.Builder builder;
    int count=0;
    public CompanyItem(Context context, Company dto) {
        super(context);
        this.dto = dto;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.company_info_item, this);
        bookMark = (ImageView) view.findViewById(R.id.company_add_friend);
        builder=new AlertDialog.Builder(context);
        setData();
    }

    public void setData() {
        TextView compIntro = (TextView) view.findViewById(R.id.intro_company);
        TextView compName = (TextView) view.findViewById(R.id.name_company);
        ImageView compImage = (ImageView) view.findViewById(R.id.image_company);
        compIntro.setText(dto.getCompany_intro());
        compName.setText(dto.getCompany_name());
        ImageAsycnTask imageAsycnTask = new ImageAsycnTask(compImage, 250);
        imageAsycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,dto.getImage_url(), "GET");
        bookMark.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView view = (ImageView) v;
                if(count==0){
                    count=1;
                    //view.setPadding(5,5,5,5);
                    view.setImageResource(R.drawable.add_friend);
                    builder.setTitle("알림").setMessage("친구추가가 되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

                }else {
                    count=0;
                    view.setImageResource(R.drawable.default_friend);
                }

            }
        });
    }

    public Company getDto() {
        return dto;
    }

    public void setDto(Company dto) {
        this.dto = dto;
    }
}
