package com.example.user.TourTalking.estimate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.sharing.InitAsycnTask;
import com.example.user.TourTalking.sharing.MainActivity;

/**
 * Created by user on 2017-03-20.
 */

public class EstimateCountryListActivity extends AppCompatActivity{
    EstmateViewPager estmateViewPager;
    ViewPager viewPager;
    public InitAsycnTask initAsycnTask;
    public static EstimateCountryListActivity estimateCountryListActivity;
    String TAG;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=this.getClass().getSimpleName();
        setContentView(R.layout.estimate_complist_activity);
        estimateCountryListActivity=this;
        initAsycnTask = new InitAsycnTask(this);
        initAsycnTask.execute("http://192.168.219.101:7777/device/init", "GET");
    }


    public void init(){
        Log.d(TAG,"EstimateCountryList init 작동");
        estmateViewPager=new EstmateViewPager(getSupportFragmentManager());
        viewPager=(ViewPager) findViewById(R.id.estmate_expanded_menu);
        viewPager.setAdapter(estmateViewPager);
    }
}
