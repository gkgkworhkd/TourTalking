package com.example.user.TourTalking.sharing;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.login.LoginActivity;

/**
 * Created by user on 2017-04-05.
 */

public class MyAppCompatActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (MainActivity.mainActivity.getMember_pk() == null) {
            getMenuInflater().inflate(R.menu.main_menu, menu);
        } else {
            getMenuInflater().inflate(R.menu.login_menu, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (MainActivity.mainActivity.getMember_pk() == null) {
            if(id==R.id.login){
                startActivity(new Intent(this, LoginActivity.class));
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }

        } else {

        }

        return super.onOptionsItemSelected(item);
    }
}
