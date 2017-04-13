package com.example.user.TourTalking.estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.domain.Estimate;

/**
 * Created by user on 2017-03-20.
 */

public class EstimatePopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.support.v7.appcompat.R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.estimate_popup);
    }

    public void estiPopup(View view) {
        if(view.getId()==R.id.est_consent){
            EstimateActivity.estimateActivity.finish();
            this.finish();
            Estimate estimate=new Estimate();
            estimate.setStartpoint("서울");
            EstimateAsycnTask asycnTask=new EstimateAsycnTask(estimate);
            asycnTask.execute("http://192.168.219.100:7777/device/insert/estimate","POST");
            startActivity(new Intent(this, EstimateResultActivity.class));
        }else {
            this.finish();
        }
    }
}
