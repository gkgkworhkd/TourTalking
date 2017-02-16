package com.example.user.myapplication.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.user.myapplication.R;
import com.example.user.myapplication.chat.ChatActivity;

/**
 * Created by user on 2017-02-15.
 */

public class ProfileActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }
    public void Profile_click(View view){
        int id=view.getId();
        if(id==R.id.profile_chat){
            Intent intent=new Intent(this, ChatActivity.class);
            startActivity(intent);
        }else if(id==R.id.profile_content){

        }
    }
}
