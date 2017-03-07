package com.example.user.TourTalking.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.chat.ChatActivity;
import com.example.user.TourTalking.domain.company.Company;
import com.example.user.TourTalking.sharing.ImageAsycnTask;

/**
 * Created by user on 2017-02-15.
 */

public class ProfileActivity extends AppCompatActivity{
    private Company dto;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Intent intent=getIntent();
        dto=intent.getParcelableExtra("compData");
        ImageView imageView=(ImageView) findViewById(R.id.profile_image);
        ImageAsycnTask asycnTask=new ImageAsycnTask(imageView);
        asycnTask.execute(dto.getImage_url(),"GET");
    }
    public void Profile_click(View view){
        int id=view.getId();
        if(id==R.id.profile_chat){
            Intent intent=new Intent(this, ChatActivity.class);
            intent.putExtra("compData",dto);
            startActivity(intent);
        }else if(id==R.id.profile_content){

        }
    }
}
