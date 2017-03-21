package com.example.user.TourTalking.estimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.sharing.MainActivity;

/**
 * Created by user on 2017-03-20.
 */

public class EstimateResultActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estimate_result_activity);
    }
    public void resultBt(View view){
        startActivity(new Intent(this, MainActivity.class).putExtra("intro", false));
    }
}
