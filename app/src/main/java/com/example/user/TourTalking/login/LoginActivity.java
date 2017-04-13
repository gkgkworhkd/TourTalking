package com.example.user.TourTalking.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.TourTalking.R;
import com.example.user.TourTalking.sharing.MainActivity;
import com.example.user.TourTalking.sharing.MyAppCompatActivity;

/**
 * Created by user on 2017-04-05.
 */

public class LoginActivity extends MyAppCompatActivity {
    private EditText logId;
    private EditText logPwd;
    private String checkId;
    private String checkPwd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        logId = (EditText) findViewById(R.id.login_id);
        logPwd = (EditText) findViewById(R.id.login_pwd);
    }

    public void btLogin(View view) {
        int id = view.getId();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        if (id == R.id.login_login) {
            if (logId.getText().toString().equals("")) {
                builder.setTitle("알림").setMessage("아이디를 입력해주세요");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            } else if (logPwd.getText().toString().equals("")) {
                builder.setTitle("알림").setMessage("비밀 번호를 입력해주세요");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            } else {
                checkId=logId.getText().toString();
                checkPwd=logPwd.getText().toString();
                DataCheckAsycnTask asycnTask=new DataCheckAsycnTask(checkId,checkPwd);
                asycnTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"http://192.168.219.100:7777/device/checkLogin","POST");
                MainActivity.mainActivity.setMember_pk(checkId);
                MainActivity.mainActivity.setMemberType("coustomer");
                builder.setTitle("알림").setMessage(checkId+"반갑 습니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
            }
        } else if (id == R.id.login_regist) {
            startActivity(new Intent(this,RegistActivity.class));
        }

    }
}
