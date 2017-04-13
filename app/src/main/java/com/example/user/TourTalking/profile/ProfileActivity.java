package com.example.user.TourTalking.profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.board.BoardListActivity;
import com.example.user.TourTalking.chat.ChatActivity;
import com.example.user.TourTalking.domain.Company;
import com.example.user.TourTalking.sharing.ImageAsycnTask;
import com.example.user.TourTalking.sharing.MainActivity;
import com.example.user.TourTalking.sharing.MyAppCompatActivity;


/**
 * Created by user on 2017-02-15.
 */

public class ProfileActivity extends MyAppCompatActivity {
    private Company dto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        Intent intent = getIntent();
        dto = intent.getParcelableExtra("compData");
        ImageView imageView = (ImageView) findViewById(R.id.profile_image);
        ImageAsycnTask asycnTask = new ImageAsycnTask(imageView);
        asycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, dto.getImage_url(), "GET");
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void Profile_click(View view) {
        int id = view.getId();
        if (id == R.id.profile_chat) {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra(ChatActivity.INDIVICHAT, dto);
            intent.putExtra("companyName", dto.getCompany_name());
            intent.putExtra("protocol", getProtocol(dto));
            //TODO 회원 로그인시 해당 회원에 대한 정보전달
            startActivity(intent);
        } else if (id == R.id.profile_content) {
            Intent intent=new Intent(this,BoardListActivity.class);
            intent.putExtra("type","1");
            startActivity(intent);
        }
    }

    public String getProtocol(Company dto) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        sb.append("\"chat-type\":\"individual\",");
        sb.append("\"target-type\":\"agency\",");
        sb.append("\"target-id\":" + Integer.toString(dto.getCompany_id()) + ",");
        sb.append("\"member-type\":\"" + MainActivity.mainActivity.memberInfo[0] + "\",");
        sb.append("\"member-id\":\"" + MainActivity.mainActivity.memberInfo[1] + "\"");
        sb.append("}");
        return sb.toString();
    }
}
