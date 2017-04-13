package com.example.user.TourTalking.login;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.TourTalking.R;

import org.w3c.dom.Text;

/**
 * Created by user on 2017-04-05.
 */

public class RegistActivity extends AppCompatActivity {
    private TextView male;
    private TextView female;
    private TextView gest;
    private TextView agency;
    private ImageView male_image;
    private ImageView gest_image;
    private ImageView female_image;
    private ImageView agency_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_regist_activity);
        init();
    }

    public void init() {
        male = (TextView) findViewById(R.id.gender_male);
        female = (TextView) findViewById(R.id.gender_female);
        gest = (TextView) findViewById(R.id.member_gest);
        agency = (TextView) findViewById(R.id.member_agency);
        male_image = (ImageView) findViewById(R.id.male_image);
        gest_image = (ImageView) findViewById(R.id.gest_image);
        female_image = (ImageView) findViewById(R.id.female_image);
        agency_image = (ImageView) findViewById(R.id.agency_image);
    }

    public void btRegist(View view) {
        switch (view.getId()) {
            case R.id.regist_reg:
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
                builder.setTitle("알림").setMessage("가입을 축하합니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
                break;
            case R.id.gender_male:
                male.setTextColor(Color.parseColor("#769dff"));
                male_image.setImageResource(R.drawable.check);
                female.setTextColor(Color.DKGRAY);
                female_image.setImageResource(0);
                break;
            case R.id.gender_female:
                female.setTextColor(Color.parseColor("#769dff"));
                female_image.setImageResource(R.drawable.check);
                male.setTextColor(Color.DKGRAY);
                male_image.setImageResource(0);
                break;
            case R.id.member_gest:
                gest.setTextColor(Color.parseColor("#769dff"));
                gest_image.setImageResource(R.drawable.check);
                agency.setTextColor(Color.DKGRAY);
                agency_image.setImageResource(0);
                break;
            case R.id.member_agency:
                agency.setTextColor(Color.parseColor("#769dff"));
                agency_image.setImageResource(R.drawable.check);
                gest.setTextColor(Color.DKGRAY);
                gest_image.setImageResource(0);
                break;
        }
    }
}
